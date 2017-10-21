	@Test
	public void testGenericApplicationContextWithXmlBeanDefinitions() {
		GenericApplicationContext ctx = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ctx);
		reader.loadBeanDefinitions(new ClassPathResource(CONTEXT_B, getClass()));
		reader.loadBeanDefinitions(new ClassPathResource(CONTEXT_C, getClass()));
		reader.loadBeanDefinitions(new ClassPathResource(CONTEXT_A, getClass()));
		ctx.refresh();
		assertTrue(ctx.containsBean("service"));
		assertTrue(ctx.containsBean("logicOne"));
		assertTrue(ctx.containsBean("logicTwo"));
		ctx.close();
	}
