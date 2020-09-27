package com.ista.isp.assessment.todo.response;

import com.ista.isp.assessment.todo.model.Item;

public class ItemWebResponse {

    private Item item;

    public ItemWebResponse() {
    }

    public ItemWebResponse(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
