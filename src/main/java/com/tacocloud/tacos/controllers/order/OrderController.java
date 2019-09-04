package com.tacocloud.tacos.controllers.order;

import com.tacocloud.tacos.data.OrderRepository;
import com.tacocloud.tacos.domain.Order;
import com.tacocloud.tacos.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/order")
@SessionAttributes("order")
public class OrderController {

	private final OrderRepository orderRepository;
	private final OrderProps props;

	@Autowired
	public OrderController(OrderRepository orderRepository, OrderProps orderProps) {
		this.orderRepository = orderRepository;
		this.props = orderProps;
	}

	@GetMapping
	public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
		Pageable pageable = PageRequest.of(0, props.getPageSize());
		model.addAttribute("orders", orderRepository.findByUserOrderByCreateDateDesc(user, pageable));

		return "orderList";
	}

	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "order/order";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
		if (errors.hasErrors()) {
			return "order/order";
		}
		log.info("Processing order: " + order);

		order.setUser(user);
		orderRepository.save(order);

		log.info("Processed order " + order.getId());
		sessionStatus.setComplete();

		return "redirect:/";
	}
}
