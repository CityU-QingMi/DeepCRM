	@Test
	public void shouldReceiveJsonAsResponseEntityList() throws Exception {
		String content = "[{\"bar\":\"bar1\",\"foo\":\"foo1\"}, {\"bar\":\"bar2\",\"foo\":\"foo2\"}]";
		prepareResponse(response -> response
				.setHeader("Content-Type", "application/json").setBody(content));

		Mono<ResponseEntity<List<Pojo>>> result = this.webClient.get()
				.uri("/json").accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMap(response -> response.toEntityList(Pojo.class));

		StepVerifier.create(result)
				.consumeNextWith(entity -> {
					assertEquals(HttpStatus.OK, entity.getStatusCode());
					assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType());
					assertEquals(58, entity.getHeaders().getContentLength());
					Pojo pojo1 = new Pojo("foo1", "bar1");
					Pojo pojo2 = new Pojo("foo2", "bar2");
					assertEquals(Arrays.asList(pojo1, pojo2), entity.getBody());
				})
				.expectComplete().verify(Duration.ofSeconds(3));

		expectRequestCount(1);
		expectRequest(request -> {
			assertEquals("/json", request.getPath());
			assertEquals("application/json", request.getHeader(HttpHeaders.ACCEPT));
		});
	}
