	@Test
	public void messageConverters() {
		loadBeanDefinitions("websocket-config-broker-converters.xml");

		CompositeMessageConverter compositeConverter = this.appContext.getBean(CompositeMessageConverter.class);
		assertNotNull(compositeConverter);

		assertEquals(4, compositeConverter.getConverters().size());
		assertEquals(StringMessageConverter.class, compositeConverter.getConverters().iterator().next().getClass());
	}
