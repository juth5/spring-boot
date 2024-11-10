package com.example.demo.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Announce;
import com.example.demo.entity.Memo;
//import com.example.demo.entity.Status; // Statusクラスもインポート

@Mapper
public interface AnnounceMapper {

    // 1. Memoのリストを取得
    @Select("SELECT id, content, user_id FROM memo")
    @Results(id = "MemoResultMap", value = {
        @Result(property = "id", column = "id"),
        @Result(property = "content", column = "content"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "statuses", javaType = List.class, column = "id",
                many = @Many(select = "selectStatusesByMemoId"))  // MemoごとにStatusを取得する設定
    })
    List<Memo> findAllWithStatuses();

    // 2. Memoに関連するStatusのリストを取得
    // @Select("SELECT id, status, memo_id FROM status WHERE memo_id = #{memoId}")
    // List<Status> selectStatusesByMemoId(Long memoId);

    // 3. Memoのリストを取得する別メソッド
    @Select("SELECT id, content FROM announce")
    List<Announce> findAll();

    // 4. Memoの内容を挿入
    @Insert("INSERT INTO announce (announce_id, user_id) VALUES (#{announce_id}, #{user_id})")
    void insertRead(Announce announce);
}
