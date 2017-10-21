	@Test
	public void getHandlerProducibleMediaTypesAttribute() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/content");
		request.addHeader("Accept", "application/xml");
		this.handlerMapping.getHandler(request);

		String name = HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE;
		assertEquals(Collections.singleton(MediaType.APPLICATION_XML), request.getAttribute(name));

		request = new MockHttpServletRequest("GET", "/content");
		request.addHeader("Accept", "application/json");
		this.handlerMapping.getHandler(request);

		assertNull("Negated expression shouldn't be listed as producible type", request.getAttribute(name));
	}
