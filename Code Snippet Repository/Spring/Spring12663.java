	@Test
	public void order() {
		this.registry.addViewController("/path");
		SimpleUrlHandlerMapping handlerMapping = this.registry.buildHandlerMapping();
		assertEquals(1, handlerMapping.getOrder());

		this.registry.setOrder(2);
		handlerMapping = this.registry.buildHandlerMapping();
		assertEquals(2, handlerMapping.getOrder());
	}
