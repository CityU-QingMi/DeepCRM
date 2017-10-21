	@Test
	public void httpEntityWithFluxBody() throws Exception {
		ServerWebExchange exchange = postExchange("line1\nline2\nline3\n");
		ResolvableType type = httpEntityType(Flux.class, String.class);
		HttpEntity<Flux<String>> httpEntity = resolveValue(exchange, type);

		assertEquals(exchange.getRequest().getHeaders(), httpEntity.getHeaders());
		StepVerifier.create(httpEntity.getBody())
				.expectNext("line1\n")
				.expectNext("line2\n")
				.expectNext("line3\n")
				.expectComplete()
				.verify();
	}
