package com.ista.isp.assessment.todo.response;

import com.ista.isp.assessment.todo.model.Item;

import java.util.List;

public class ItemsWebResponse {
    private long totalItems;
    private int totalPages;
    private int currentPage;
    private List<Item> items;

    public ItemsWebResponse(long totalItems, int totalPages, int currentPage, List<Item> items) {
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ItemsWebResponse{");
        sb.append("totalItems=").append(totalItems);
        sb.append(", totalPages=").append(totalPages);
        sb.append(", currentPage=").append(currentPage);
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}
