package demo.fresh.finds.services;

import demo.fresh.finds.entities.ItemEntity;
import demo.fresh.finds.model.Item;
import demo.fresh.finds.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    public List<Item> getAllItems() {
        List<ItemEntity> itemEntity = itemRepository.findAll();

        return Collections.singletonList(this.mapToDto((ItemEntity) itemEntity));
    }
    public Item saveItem(Item item) {
        ItemEntity itemEntity = mapToEntity(item);
        itemEntity = itemRepository.save(itemEntity);
        return this.mapToDto(itemEntity);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Item mapToDto(ItemEntity itemEntity) {
        Item item = new Item();
        item.setItemName(itemEntity.getItemName());
        return item;
    }

    public ItemEntity mapToEntity(Item item) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemName(item.getItemName());
        return itemEntity;
    }
}
