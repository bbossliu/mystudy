package com.mystudy.lx.service.impl;


import com.mystudy.lx.mapper.TbContentCategoryMapper;
import com.mystudy.lx.service.TbContentCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @Resource
    private TbContentCategoryMapper tbContentCategoryMapper;

}
