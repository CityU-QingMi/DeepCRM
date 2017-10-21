	@Test
	public void getHandlerProducibleMediaTypesAttribute() throws Exception {
		ServerWebExchange exchange = MockServerWebExchange.from(get("/content").accept(MediaType.APPLICATION_XML).build());
		this.handlerMapping.getHandler(exchange).block();

		String name = HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE;
		assertEquals(Collections.singleton(MediaType.APPLICATION_XML), exchange.getAttributes().get(name));

		exchange = MockServerWebExchange.from(get("/content").accept(MediaType.APPLICATION_JSON).build());
		this.handlerMapping.getHandler(exchange).block();

		assertNull("Negated expression shouldn't be listed as producible type",
				exchange.getAttributes().get(name));
	}
