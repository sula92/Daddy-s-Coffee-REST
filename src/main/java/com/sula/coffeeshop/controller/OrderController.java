package com.sula.coffeeshop.controller;

import com.sula.coffeeshop.dto.ItemDTO;
import com.sula.coffeeshop.dto.OrderDTO;
import com.sula.coffeeshop.dto.OrderDetailDTO;
import com.sula.coffeeshop.entity.OrderDetail;
import com.sula.coffeeshop.service.custom.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(value="/searchOrders/{oid}")
    public List<OrderDTO> searchOrder(@PathVariable String oid) throws Exception {
        return orderService.searchOrder(oid);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void placeOrder(@ModelAttribute @Valid OrderDTO orderDTO, @ModelAttribute @Valid List<OrderDetailDTO> orderDetailDTOS ) throws Exception {

        orderService.placeOrder(orderDTO,orderDetailDTOS);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{code}",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void updateOrder(@PathVariable @Valid @Pattern(regexp = "OD\\{3}") String code, @RequestBody MultiValueMap<String,String> params) throws Exception {
        if (!orderService.orderExist(code)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        orderService.updateOrder(params.get("description").get(0),Integer.parseInt(params.get("qtyOnHand").get(0)),Double.parseDouble(params.get("unitPrice").get(0)),code);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{oid}")
    public void deleteItem(@PathVariable @Valid @Pattern(regexp = "OD\\{3}") String oid) throws Exception {
        if (!orderService.orderExist(oid)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        orderService.deleteOrder(oid);
    }



}
