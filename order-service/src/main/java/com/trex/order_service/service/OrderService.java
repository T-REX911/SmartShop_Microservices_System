package com.trex.order_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trex.order_service.dto.ItemDto;
import com.trex.order_service.dto.OrderDto;
import com.trex.order_service.dto.ProductDto;
import com.trex.order_service.feign.ProductInterface;
import com.trex.order_service.model.Item;
import com.trex.order_service.model.Order;
import com.trex.order_service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductInterface productInterface;

    public ResponseEntity<?> createOrder(OrderDto dto) {

        List<ProductDto> productInfo = getProductInfo(dto.getItemDtoList());

        boolean productsAvailable = isAllProductsAvailable(dto.getItemDtoList(),productInfo);

        if(productsAvailable){
            Order order = new Order();
            List<Item> newItems = productInfo.stream().map(product -> {
                Item item = new Item();
                item.setProductId(product.getId());
                for (long itemId:dto.getItemDtoList().stream().map(i->i.getId()).collect(Collectors.toList())){
                    if(itemId == product.getId()){
                        Optional<ItemDto> matchingItem = dto.getItemDtoList().stream()
                                .filter(x -> x.getId() == itemId) // find the matching item
                                .findFirst();

                        item.setQty(matchingItem.map(ItemDto::getQty) // get qty if present
                                .orElse(0));

                    }
                }
                item.setOrder(order);
                return item;
            }).collect(Collectors.toList());

            order.setStatus("New");
            order.setItems(newItems);

            Order save = orderRepository.save(order);
            return new ResponseEntity<>(save,HttpStatus.OK);
        }else{

            return new ResponseEntity<>("Order can not be placed", HttpStatus.BAD_REQUEST);
        }

    }

    private List<ProductDto> getProductInfo(List<ItemDto> itemDto) {

        List<?> collect = itemDto.stream().map(item -> {
            Optional<ResponseEntity<?>> product = Optional.ofNullable(productInterface.getProductById(item.getId()));
            if(product.isPresent()){
                return product.get().getBody();
            }
            return new ProductDto();
        }).collect(Collectors.toList());

        List<ProductDto> collect1 = collect.stream().map(product -> {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.convertValue(product, ProductDto.class);
        }).collect(Collectors.toList());

        return collect1;
    }

    private boolean isAllProductsAvailable(List<ItemDto>  items, List<ProductDto> productInfo){

        List<Long> itemIdList = items.stream().map(item -> item.getId()).collect(Collectors.toList());
        List<Long> productIdList = productInfo.stream().map(product -> product.getId()).collect(Collectors.toList());

        List<Boolean> collect = itemIdList.stream().map(item -> {
            return productIdList.stream().anyMatch(product -> product == item);
        }).collect(Collectors.toList());

        boolean b = collect.stream().anyMatch(x -> false);


        return !b;
    }
}
