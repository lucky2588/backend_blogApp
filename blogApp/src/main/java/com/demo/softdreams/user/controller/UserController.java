package com.demo.softdreams.user.controller;

import com.demo.softdreams.core.dto.FavoritesDTO;
import com.demo.softdreams.core.dto.UserInfo;
import com.demo.softdreams.core.entites.Favorites;
import com.demo.softdreams.core.exception.BadResquestException;
import com.demo.softdreams.shared.respone.ChangeInfoUser;
import com.demo.softdreams.shared.respone.ChangePassword;
import com.demo.softdreams.user.service.FavoritesService;
import com.demo.softdreams.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private FavoritesService serviceFavorites;

    // get All
    @PostMapping("changeInfo")
    public UserInfo changeInfo(@RequestBody ChangeInfoUser res) {
        return service.changeInfoUser(res);
    }

    @GetMapping("{id}")
    public UserInfo findDetail(@PathVariable Long id) {
        return service.findUserById(id);
    }

    @PostMapping("changePassword")
    public UserInfo changePassword(@RequestBody ChangePassword res) throws BadResquestException {
        return service.changePassword(res);
    }

    @GetMapping("checkFavorites")
    public boolean checkFavorites(
            @RequestParam Long userId,
            @RequestParam Long targetBlog
    ) throws BadResquestException {
      if(serviceFavorites.getFavoritesDetail(userId,targetBlog) == null){
          return true;
      }
      return false;
    }


    @GetMapping("addFavorites")
    public void addFavorites(@RequestParam Long userId, @RequestParam Long targetBlog) throws BadResquestException {
         serviceFavorites.saveFavorites(userId, targetBlog);
    }


}


