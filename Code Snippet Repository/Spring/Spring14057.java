	@Test
	public void messageConvertersDefaultsOff() {
		loadBeanDefinitions("websocket-config-broker-converters-defaults-off.xml");

		CompositeMessageConverter compositeConverter = this.appContext.getBean(CompositeMessageConverter.class);
		assertNotNull(compositeConverter);

		assertEquals(1, compositeConverter.getConverters().size());
		assertEquals(StringMessageConverter.class, compositeConverter.getConverters().iterator().next().getClass());
	}
