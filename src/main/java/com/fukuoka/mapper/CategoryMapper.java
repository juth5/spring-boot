package com.fukuoka.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import com.fukuoka.entity.Category;


// MyBatisでインターフェースを使う理由
// MyBatisのMapperはデータベースと直接やり取りするための**契約（インターフェース）**を定義するものです。具体的なデータベース操作の実装はMyBatisフレームワークが自動的に生成します。そのため、Mapperはインターフェースとして定義し、SQL操作やマッピングをMyBatisが担当します。

// このように、インターフェースを使うことでコードをシンプルに保ちつつ、実際の処理はMyBatisに任せることができるため、効率的にデータベースアクセスを行うことができます。
@Mapper
public interface CategoryMapper {

    @Select("SELECT * FROM category")
    List<Category> findAll();

    @Insert("INSERT INTO category(name, image_url) VALUES(#{name}, #{imageUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Category category);

}