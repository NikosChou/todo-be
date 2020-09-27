package com.ista.isp.assessment.todo.controller;

import com.ista.isp.assessment.todo.enums.ItemStatusEnum;
import com.ista.isp.assessment.todo.model.Item;
import com.ista.isp.assessment.todo.request.CreateNewItemWebRequest;
import com.ista.isp.assessment.todo.response.ItemWebResponse;
import com.ista.isp.assessment.todo.response.ItemsWebResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerITest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllItems() {
        final ItemsWebResponse itemsWebResponse = restTemplate.getForObject("http://localhost:" + port + "/api/item", ItemsWebResponse.class);

        assertThat(itemsWebResponse).isNotNull();
        assertThat(itemsWebResponse.getTotalItems()).isEqualTo(6);
        assertThat(itemsWebResponse.getTotalPages()).isEqualTo(1);
        assertThat(itemsWebResponse.getCurrentPage()).isEqualTo(0);
        assertThat(itemsWebResponse.getItems().size()).isEqualTo(6);
    }

    @Test
    public void getItem() {
        final ItemsWebResponse itemsWebResponse = restTemplate.getForObject("http://localhost:" + port + "/api/item", ItemsWebResponse.class);
        final Item firstItem = itemsWebResponse.getItems().get(0);

        final ItemWebResponse itemWebResponse = restTemplate.getForObject("http://localhost:" + port + "/api/item/" + firstItem.getId().toString(), ItemWebResponse.class);
        assertThat(itemWebResponse).isNotNull();
        assertThat(itemWebResponse.getItem()).isEqualTo(firstItem);
    }

    @Test
    public void createNewItem() {
        final CreateNewItemWebRequest request = new CreateNewItemWebRequest();
        final String title = "test title";
        final LocalDateTime dueDate = LocalDateTime.now().plusDays(1);
        request.setDueDate(dueDate);
        request.setTitle(title);
        final ResponseEntity<ItemWebResponse> itemWebResponseResponseEntity = restTemplate.postForEntity("http://localhost:" + port + "/api/item", request, ItemWebResponse.class);
        final ItemWebResponse itemWebResponse = itemWebResponseResponseEntity.getBody();
        assertThat(itemWebResponse).isNotNull();

        assertThat(itemWebResponse.getItem().getTitle()).isEqualTo(title);
        assertThat(itemWebResponse.getItem().getDueDate()).isEqualTo(dueDate);
        assertThat(itemWebResponse.getItem().getId()).isNotNull();
        assertThat(itemWebResponse.getItem().getCreatedAt().toLocalDate()).isEqualTo(LocalDate.now());
        assertThat(itemWebResponse.getItem().getStatuses()).isNotEmpty();
        assertThat(itemWebResponse.getItem().getStatuses().get(0).getName()).isEqualTo(ItemStatusEnum.CREATED.getValue());
    }

    @Test
    public void updateItem() {
        final ItemsWebResponse itemsWebResponse = restTemplate.getForObject("http://localhost:" + port + "/api/item", ItemsWebResponse.class);
        final Item firstItem = itemsWebResponse.getItems().get(0);

        final CreateNewItemWebRequest request = new CreateNewItemWebRequest();
        final String title = "updated title";
        final LocalDateTime dueDate = LocalDateTime.now().plusDays(1);
        request.setDueDate(dueDate);
        request.setTitle(title);

        restTemplate.put("http://localhost:" + port + "/api/item/" + firstItem.getId().toString(), request);
        final ItemWebResponse itemWebResponse = restTemplate.getForObject("http://localhost:" + port + "/api/item/" + firstItem.getId().toString(), ItemWebResponse.class);
        assertThat(itemWebResponse).isNotNull();
        assertThat(itemWebResponse.getItem().getTitle()).isEqualTo(title);
        assertThat(itemWebResponse.getItem().getDueDate()).isEqualTo(dueDate);
        assertThat(itemWebResponse.getItem().getId()).isNotNull();
        assertThat(itemWebResponse.getItem().getCreatedAt()).isEqualTo(firstItem.getCreatedAt());
    }

    @Test
    public void deleteItem() {
        final ItemsWebResponse itemsWebResponse = restTemplate.getForObject("http://localhost:" + port + "/api/item", ItemsWebResponse.class);
        final Item firstItem = itemsWebResponse.getItems().get(0);

        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/api/item/" + firstItem.getId().toString(), String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        restTemplate.delete("http://localhost:" + port + "/api/item/" + firstItem.getId().toString());
        final ResponseEntity<String> deletedResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/api/item/" + firstItem.getId().toString(), String.class);
        assertThat(deletedResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
