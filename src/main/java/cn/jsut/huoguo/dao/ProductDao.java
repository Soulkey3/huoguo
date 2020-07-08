package cn.jsut.huoguo.dao;

import cn.jsut.huoguo.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品操作持久层
 */
@Mapper
public interface ProductDao {

    /**
     * 查询所有商品信息
     * @return
     */
    List<Product> findAll();

    /**
     * 根据id查询菜品
     * @return
     */
    Product findById(@Param("productId") int productId);

    /**
     * 添加商品
     */
    void addProduct( Product product);

    /**
     * 根据id删除商品
     */
    void deleteProductById(@Param("productId") int productId);


    /**
     * 修改商品信息
     */
    void updateProduct(@Param("product") Product product);

    /**
     * 分类查询
     */
    List<Product> findProductByCategory(@Param("category") String category);
}

