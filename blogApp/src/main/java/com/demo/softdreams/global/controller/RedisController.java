//package com.demo.softdreams.global.controller;
//
//import com.demo.softdreams.administrator.dto.blog.SaveBlogReq;
//import com.demo.softdreams.administrator.dto.other.CategoryDetail;
//import com.demo.softdreams.core.entites.Favorites;
//import com.demo.softdreams.shared.respone.LoginResquest;
//import com.demo.softdreams.shared.respone.RedisRes;
// import com.demo.softdreams.shared.service.impl.RedisServiceImpl;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("api/public/favorites")
//public class RedisController {
//    @Autowired
//    private FavoritesService service;
//
//
//
//
//    @PostMapping("save")
//    public void saveObjforRedis(@RequestBody Favorites rep) throws JsonProcessingException {
//        service.setForRedis(rep);
//    }
//
//
//
//
//    @GetMapping("{id}")
//    public Favorites getFavoritesById(@PathVariable Integer id){
//        return service.getFavoritesById(id);
//    }
//
//
//@GetMapping("getAll/{userId}")
//    public ResponseEntity<?> getAll(@PathVariable  Long userId){
//    return service.getList(userId);
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
