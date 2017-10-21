	@Test
	public void testGenericApplicationContextWithXmlBeanDefinitionsAndClassLoaderNull() {
		GenericApplicationContext ctx = new GenericApplicationContext();
		ctx.setClassLoader(null);
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ctx);
		reader.loadBeanDefinitions(new ClassPathResource(CONTEXT_B, getClass()));
		reader.loadBeanDefinitions(new ClassPathResource(CONTEXT_C, getClass()));
		reader.loadBeanDefinitions(new ClassPathResource(CONTEXT_A, getClass()));
		ctx.refresh();
		assertEquals(ObjectUtils.identityToString(ctx), ctx.getId());
		assertEquals(ObjectUtils.identityToString(ctx), ctx.getDisplayName());
		assertTrue(ctx.containsBean("service"));
		assertTrue(ctx.containsBean("logicOne"));
		assertTrue(ctx.containsBean("logicTwo"));
		ctx.close();
	}
