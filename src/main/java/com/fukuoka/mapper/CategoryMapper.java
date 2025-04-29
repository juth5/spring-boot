package com.fukuoka.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fukuoka.entity.Category;


// MyBatisでインターフェースを使う理由
// MyBatisのMapperはデータベースと直接やり取りするための**契約（インターフェース）**を定義するものです。具体的なデータベース操作の実装はMyBatisフレームワークが自動的に生成します。そのため、Mapperはインターフェースとして定義し、SQL操作やマッピングをMyBatisが担当します。

// このように、インターフェースを使うことでコードをシンプルに保ちつつ、実際の処理はMyBatisに任せることができるため、効率的にデータベースアクセスを行うことができます。
@Mapper
public interface CategoryMapper {

    @Select("SELECT * FROM category WHERE id = #{id} AND del_flg = false")
    Category findById(@Param("id") Long id);

    @Select("SELECT * FROM category WHERE del_flg = false")
    List<Category> findAll();

    @Insert("INSERT INTO category(name, image_url) VALUES(#{name}, #{imageUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Category category);

    @Update("UPDATE category SET name = #{name}, image_url = #{imageUrl} WHERE id = #{id}")
    void update(Category category);

    @Delete("UPDATE category SET del_flg = true WHERE id = #{id} AND del_flg = false")
    int logicalDeleteById(Long id);
}