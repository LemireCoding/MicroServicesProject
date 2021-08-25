package com.lemire.microservices.core.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.print.attribute.standard.Media;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
class ProductServiceApplicationTests {


	private static final int PRODUCT_ID_OKAY = 1;
	private static final int PRODUCT_ID_NOT_FOUND = 13;
	private static final int PRODUCT_ID_INVALID = 3;
	private static final int PRODUCT_ID_NAGATIVE_VALUE = -1;

	@Autowired
	private WebTestClient client;

	@Test void getProductById(){
		client.get()
				.uri("/product/"+PRODUCT_ID_OKAY)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.productId").isEqualTo(PRODUCT_ID_OKAY);
	}

	@Test
	public void getProductInvalidParameterString(){
		client.get()
				.uri("/product/not-integer")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/product/not-integer");
	}

	@Test
	public void getProductNotFound(){
		client.get()
				.uri("/product/"+PRODUCT_ID_NOT_FOUND)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/product/"+PRODUCT_ID_NOT_FOUND)
				.jsonPath("$.message").isEqualTo("No product found for productID : "+PRODUCT_ID_NOT_FOUND);
	}

	/**
	@Test
	public void getProductInvalidParameterNegativeValue(){
		client.get()
				.uri("/product/"+PRODUCT_ID_NAGATIVE_VALUE)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/product/"+PRODUCT_ID_NAGATIVE_VALUE)
				.jsonPath("$.message").isEqualTo("Invalid productId : "+PRODUCT_ID_NAGATIVE_VALUE);

	}
**/
	@Test
	void contextLoads() {
	}

}
