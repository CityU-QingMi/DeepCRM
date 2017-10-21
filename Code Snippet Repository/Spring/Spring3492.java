	@Test
	public void testGenericApplicationContextWithXmlBeanDefinitionsAndSpecifiedId() {
		GenericApplicationContext ctx = new GenericApplicationContext();
		ctx.setId("testContext");
		ctx.setDisplayName("Test Context");
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ctx);
		reader.loadBeanDefinitions(new ClassPathResource(CONTEXT_B, getClass()));
		reader.loadBeanDefinitions(new ClassPathResource(CONTEXT_C, getClass()));
		reader.loadBeanDefinitions(new ClassPathResource(CONTEXT_A, getClass()));
		ctx.refresh();
		assertEquals("testContext", ctx.getId());
		assertEquals("Test Context", ctx.getDisplayName());
		assertTrue(ctx.containsBean("service"));
		assertTrue(ctx.containsBean("logicOne"));
		assertTrue(ctx.containsBean("logicTwo"));
		ctx.close();
	}
