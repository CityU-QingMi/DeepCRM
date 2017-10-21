	@Test
	public void producibleMediaTypesRequestAttribute() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/path").build());
		exchange.getAttributes().put(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, Collections.singleton(IMAGE_GIF));

		List<MediaType> mediaTypes = Arrays.asList(IMAGE_JPEG, IMAGE_GIF, IMAGE_PNG);
		MediaType actual = resultHandler.selectMediaType(exchange, () -> mediaTypes);

		assertEquals(IMAGE_GIF, actual);
	}
