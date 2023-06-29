package com.zqz.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.zqz.mall.common.bean.GetCategoryDataResp;
import com.zqz.mall.common.bean.GetCategoryDataVo;
import com.zqz.mall.common.bean.SecondLevelCategoryVo;
import com.zqz.mall.common.bean.ThirdLevelCategoryVo;
import com.zqz.mall.dao.GoodsCategoryMapper;
import com.zqz.mall.entity.GoodsCategory;
import com.zqz.mall.enums.CategoryLevelEnum;
import com.zqz.mall.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: CategoryServiceImpl
 * @Date: Created in 14:32 2023-6-29
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private GoodsCategoryMapper categoryMapper;


    @Override
    public GetCategoryDataResp getCategoryData() {
        GetCategoryDataResp resp = new GetCategoryDataResp();
        List<GetCategoryDataVo> categoryDataList = new ArrayList<>();
        //获取一级分类的固定数量的数据
        List<GoodsCategory> firstCategoryList = categoryMapper.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), CategoryLevelEnum.LEVEL_ONE.getLevel(), 10);
        if (CollectionUtil.isNotEmpty(firstCategoryList)) {
            List<Long> firstCategoryIds = firstCategoryList.stream().map(GoodsCategory::getId).collect(Collectors.toList());
            //获取二级分类的数据
            List<GoodsCategory> secondCategoryList = categoryMapper.selectByLevelAndParentIdsAndNumber(firstCategoryIds, CategoryLevelEnum.LEVEL_TWO.getLevel(), 10);
            if (CollectionUtil.isNotEmpty(secondCategoryList)) {
                List<Long> secondCategoryIds = secondCategoryList.stream().map(GoodsCategory::getId).collect(Collectors.toList());
                //获取三级分类的数据
                List<GoodsCategory> thirdCategoryList = categoryMapper.selectByLevelAndParentIdsAndNumber(secondCategoryIds, CategoryLevelEnum.LEVEL_THREE.getLevel(), 10);
                if (CollectionUtil.isNotEmpty(thirdCategoryList)) {
                    //根据 parentId 将 thirdCategoryList 分组
                    Map<Long, List<GoodsCategory>> thirdCategoryMap = thirdCategoryList.stream().collect(groupingBy(GoodsCategory::getParentId));
                    List<SecondLevelCategoryVo> secondVoList = new ArrayList<>();
                    //处理二级分类
                    secondCategoryList.forEach(second -> {
                        SecondLevelCategoryVo secondVo = new SecondLevelCategoryVo();
                        BeanUtil.copyProperties(second, secondVo);
                        secondVo.setCategoryId(second.getId());
                        //如果该二级分类下有数据则放入 secondVoList 对象中
                        if (thirdCategoryMap.containsKey(second.getId())) {
                            //根据二级分类的id取出thirdCategoryMap分组中的三级分类list
                            List<GoodsCategory> tempGoodsCategoryList = thirdCategoryMap.get(second.getId());
                            List<ThirdLevelCategoryVo> tlcList = new ArrayList<>();
                            tempGoodsCategoryList.forEach(t -> {
                                ThirdLevelCategoryVo tlc = new ThirdLevelCategoryVo();
                                BeanUtil.copyProperties(t, tlc);
                                tlc.setCategoryId(t.getId());
                                tlcList.add(tlc);
                                secondVo.setThirdLevelCategoryVOS(tlcList);
                            });
                            secondVoList.add(secondVo);
                        }
                    });
                    //处理一级分类
                    if (CollectionUtil.isNotEmpty(secondVoList)) {
                        //根据 parentId 将 secondVoList 分组
                        Map<Long, List<SecondLevelCategoryVo>> secondVoMap = secondVoList.stream().collect(groupingBy(SecondLevelCategoryVo::getParentId));
                        firstCategoryList.forEach(first -> {
                            GetCategoryDataVo categoryData = new GetCategoryDataVo();
                            BeanUtil.copyProperties(first, categoryData);
                            categoryData.setCategoryId(first.getId());
                            //如果该一级分类下有数据则放入 CategoryDataList 对象中
                            if (secondVoMap.containsKey(first.getId())) {
                                //根据一级分类的id取出secondVoMap分组中的二级级分类list
                                categoryData.setSecondLevelCategoryVOS(secondVoMap.get(first.getId()));
                                categoryDataList.add(categoryData);
                            }
                        });
                    }
                }
            }
        }
        resp.setData(categoryDataList);
        return resp;
    }
}
