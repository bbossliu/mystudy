package com.mystudy.lx.mapper;

import com.mystudy.lx.entity.Do.TtlProductInfoDo;

import java.util.List;
import java.util.Map;

/**
 * @author dalaoban
 * @create 2020-05-11-23:08
 */
public interface TtlProductInfoMapper {

    List<TtlProductInfoDo> listProduct(Map<String, Object> map);

}