/**
 * All rights Reserved, Designed By MiGu
 * Copyright: Copyright(C) 2016-2020
 * Company    MiGu  Co., Ltd.
 * <p>
 * Date:2019-08-13
 */
package com.db.database.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lk
 * @version 1.0.0
 * @since jdk8
 */

public class BeanConvertorUtils {

    public static <Model, VO> List<VO> toVOList(List<Model> modelList, Class<VO> voClass) {
        if (CollectionUtils.isEmpty(modelList)) {
            return Collections.emptyList();
        }

        return modelList.stream().map((model) -> toVO(model, voClass)).collect(Collectors.toList());
    }

    public static <Model, VO> List<Model> toModelList(List<VO> voList, Class<Model> modelClass) {
        if (CollectionUtils.isEmpty(voList)) {
            return Collections.emptyList();
        }

        return voList.stream().map((vo) -> toModel(vo, modelClass)).collect(Collectors.toList());
    }

    /**
     * 封装分页
     */
//    public static <Model, VO> PageBean<VO> toPageBeanVO(Page<Model> modelPage, Class<VO> voClass) {
//        if (modelPage == null) {
//            return PageBean.empty();
//        }
//
//        List<Model> modelList = modelPage.getContent();
//        List<VO> voList = toVOList(modelList, voClass);
//
//        return PageBean.ok(modelPage.getTotalPages(), modelPage.getTotalElements(), voList);
//    }
    public static <Model, VO> Model toModel(VO vo, Class<Model> modelClass) {
        if (vo == null) {
            return null;
        }

        Model model = BeanUtils.instantiateClass(modelClass);
        BeanUtils.copyProperties(vo, model);

        return model;
    }

    public static <Model, VO> VO toVO(Model model, Class<VO> voClass) {
        if (model == null) {
            return null;
        }

        VO vo = BeanUtils.instantiateClass(voClass);
        BeanUtils.copyProperties(model, vo);
        return vo;
    }
}