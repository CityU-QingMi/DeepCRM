	@Test
	public void handlerMapping() {
		ApplicationContext config = createConfig(TestChannelConfig.class, TestConfigurer.class);
		SimpleUrlHandlerMapping hm = (SimpleUrlHandlerMapping) config.getBean(HandlerMapping.class);
		assertEquals(1, hm.getOrder());

		Map<String, Object> handlerMap = hm.getHandlerMap();
		assertEquals(1, handlerMap.size());
		assertNotNull(handlerMap.get("/simpleBroker"));
	}
