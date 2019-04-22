package com.example.demo.demoboot2.dao;

import com.example.demo.demoboot2.form.ProductForm;
import com.example.demo.demoboot2.vo.ProductVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: lichangtong
 * @Date: 2019-03-20 11:22
 * @Description:
 */
@Repository
public interface ProductMapper {
    List<ProductVO> queryProductList(ProductForm form);
}
