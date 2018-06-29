package com.example.user;

import com.example.order.OrderService;
import com.example.order.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private OrderService orderService;

    @Autowired
    public UserController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }


    /// @RequestBody用作application/json或者是application/xml等
    /// 不加@RequestBody form-data,x-www-form-urlencoded等。
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody User user) {
        Long id = userService.saveUser(user);
        if (id > 0)
            return ResponseEntity.status(HttpStatus.CREATED).body(String.valueOf(id));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Long> updateUser(@RequestBody User user) {
        Long id = userService.updateUser(user);
        if (id > 0)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        // 这里，如果
        // user.某个操作，
        //  catch 不住异常？
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }

    // 数据库里需要定义成ROLE_USER
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<List<User>> getUser() {
        List<User> users = userService.listUser();
        if (users == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(users);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('USER')")
    public Page<Orders> getOrders(@RequestAttribute("currentUser") long id,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "20") int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return orderService.getListByUser(id, pageable);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.PUT)
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<Void> updateOrders(@RequestBody Orders orders) {
        boolean ok = orderService.updateOrder(orders);
        if (ok)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
