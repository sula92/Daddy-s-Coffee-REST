package com.sula.coffeeshop.service.custom.impl;

import com.sula.coffeeshop.dto.ItemDTO;
import com.sula.coffeeshop.entity.Item;
import com.sula.coffeeshop.repository.custom.ItemRepository;
import com.sula.coffeeshop.service.custom.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public String getNewItemCode() throws Exception {
        String lastItemCode = itemRepository.getFirstLastItemCodeByOrderByCodeDesc().getCode();
        if (lastItemCode == null) {
            return "I001";
        } else {
            int maxId = Integer.parseInt(lastItemCode.replace("I", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "I00" + maxId;
            } else if (maxId < 100) {
                id = "I0" + maxId;
            } else {
                id = "I" + maxId;
            }

            return id;
        }
    }

    @Transactional(readOnly = true)
    public List<ItemDTO> getAllItems() throws Exception {


        List<Item> allItems = itemRepository.findAll();
        List<ItemDTO> items = new ArrayList<>();
        for (Item item : allItems) {
            items.add(new ItemDTO(item.getCode(), item.getDescription(), item.getQtyOnHand(),item.getUnitPrice()));
        }
        return items;
    }

    public void saveItem(String code, String description, int qtyOnHand, double unitPrice) throws Exception {
        itemRepository.save(new Item(code, description, BigDecimal.valueOf(unitPrice), qtyOnHand));

    }

    public void deleteItem(String itemCode) throws Exception {

        itemRepository.deleteById(itemCode);

    }

    public void updateItem(String description, int qtyOnHand, double unitPrice, String itemCode) throws Exception {

        itemRepository.save(new Item(itemCode, description, BigDecimal.valueOf(unitPrice), qtyOnHand));

    }

    @Override
    public ItemDTO getItem(String code) {
        Item item = itemRepository.findById(code).get();
        return new ItemDTO(item.getCode(),item.getDescription(),item.getQtyOnHand(),item.getUnitPrice());
    }

    @Override
    public boolean itemExist(String code) {
        try {
            itemRepository.findById(code).get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
