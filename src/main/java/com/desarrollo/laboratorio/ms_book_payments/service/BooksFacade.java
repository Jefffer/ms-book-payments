package com.desarrollo.laboratorio.ms_book_payments.service;

import com.desarrollo.laboratorio.ms_book_payments.model.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class BooksFacade {

    @Value("${api.url.ms-book-catalogue}")
    private String catalogueUrl;

    @Autowired
    private RestTemplate restTemplate;

    public boolean booksAreValid(List<OrderDTO> orderDTO) {
        try{
            HttpEntity<List<OrderDTO>> request = new HttpEntity<>(orderDTO);
            ResponseEntity<Boolean> response = restTemplate.exchange(
                    catalogueUrl+"/books/update-stock",
                    HttpMethod.POST,
                    request,
                    Boolean.class);

            return response.getBody() != null && response.getBody();

        }catch (HttpClientErrorException e) {
            log.error("Client Error: {}", e.getStatusCode());
            return false;
        } catch (HttpServerErrorException e) {
            log.error("Server Error: {}", e.getStatusCode());
            return false;
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return false;
        }

    }
}
