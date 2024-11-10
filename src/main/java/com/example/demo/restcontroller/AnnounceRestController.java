package com.example.demo.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Announce;
import com.example.demo.entity.Response;

import com.example.demo.mapper.AnnounceMapper;

//@CrossOrigin(origins = "*")
@RestController
public class AnnounceRestController {

  @Autowired
  AnnounceMapper announceMapper;

  @GetMapping("/api/announces")
    public List<Announce> getAnnounces() {
      List<Announce> announces = announceMapper.findAll();
      return announces;
    }

@PostMapping("/api/announce/read")
public ResponseEntity<Response<Map<String, Long>>> receiveAnnounce(@RequestBody Map<String, Object> payload) {
    Long userId = ((Number) payload.get("userId")).longValue();
    Long announceId = ((Number) payload.get("announceId")).longValue();
    
    // ログ出力
    System.out.println("User ID: " + userId + ", Announce ID: " + announceId);

    // dataとして返す内容を準備
    Map<String, Long> data = new HashMap<>();
    data.put("userId", userId);
    data.put("announceId", announceId);

    // Responseオブジェクトを作成して返す
    Response<Map<String, Long>> response = new Response<Map<String, Long>>("success", "Data received successfully", data);

    return ResponseEntity.ok(response);
}


}
