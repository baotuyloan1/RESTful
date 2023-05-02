package payroll;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  private final OrderRepository orderRepository;
  private final OrderModelAssembler assembler;

  OrderController(OrderRepository orderRepository, OrderModelAssembler assembler) {
    this.orderRepository = orderRepository;
    this.assembler = assembler;
  }

  @GetMapping("/orders")
  CollectionModel<EntityModel<Order>> all() {
    List<EntityModel<Order>> orders =
        orderRepository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
    return CollectionModel.of(orders, linkTo(methodOn(OrderController.class).all()).withSelfRel());
  }

  @GetMapping("/orders/{id}")
  EntityModel<Order> one(@PathVariable Long id) {
    Order order = orderRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    return assembler.toModel(order);
  }
}
