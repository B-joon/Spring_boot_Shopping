package com.example.shopping_mall.controller;

import com.example.shopping_mall.service.OrderService;
import com.example.shopping_mall.vo.OrderHistVO;
import com.example.shopping_mall.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /*
    Spring에서 비동기 처리를 할 때 @RequestBody와 @ResponseBody 어노테이션을 사용한다.
        * @RequestBody : HTTP 요청의 본문 body에 담긴 내용을 자바 객체로 전달.
        * @ResponseBody : 자바 객체를 HTTP 요청의 body로 전달.
     */
    @PostMapping(value = "/order")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderVO orderVO,
                                              BindingResult bindingResult, Principal principal) {

        // 주문 정보를 받는 orderVO 객체에 데이터 바인딩 시 에러가 있는지 검사한다.
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            return  new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        /*
        현재 로그인 유저의 정보를 얻기 위해서 @Controller 어노테이션이 선언된 클래스에서 메소드 인자로
        principal 객체를 넘겨 줄 경우 해당 객체에 접근할 수 있다.
         */
        String email = principal.getName();
        Long orderId;

        try {
            orderId = orderService.Order(orderVO, email);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

    @GetMapping(value = {"/orders", "/orders/{page}"})
    public String orderHist(@PathVariable("page") Optional<Integer> page, Principal principal, Model model) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4);

        Page<OrderHistVO> orderHistVOList = orderService.getOrderList(principal.getName(), pageable);

        model.addAttribute("orders", orderHistVOList);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("maxPage", 5);

        return "order/orderHist";
    }

    @PostMapping(value = "/order/{orderId}/cancle")
    public @ResponseBody ResponseEntity cancelOrder(@PathVariable("orderId") Long orderId, Principal principal) {

        if (!orderService.validateOrder(orderId, principal.getName())) {
            return new ResponseEntity<String>("주문 취소 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        orderService.cancleOrder(orderId);

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

}
