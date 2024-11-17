package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Memo;


// MyBatisでインターフェースを使う理由
// MyBatisのMapperはデータベースと直接やり取りするための**契約（インターフェース）**を定義するものです。具体的なデータベース操作の実装はMyBatisフレームワークが自動的に生成します。そのため、Mapperはインターフェースとして定義し、SQL操作やマッピングをMyBatisが担当します。

// このように、インターフェースを使うことでコードをシンプルに保ちつつ、実際の処理はMyBatisに任せることができるため、効率的にデータベースアクセスを行うことができます。
@Mapper
public interface MemoMapper {

    @Select("SELECT * FROM memo")
    List<Memo> findAll();
}