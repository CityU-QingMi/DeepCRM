	@Test
	public void importWithPlaceholder() throws Exception {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		PropertySource<?> propertySource = new MapPropertySource("test",
				Collections.<String, Object> singletonMap("test", "springframework"));
		ctx.getEnvironment().getPropertySources().addFirst(propertySource);
		ctx.register(ImportXmlConfig.class);
		ctx.refresh();
		assertTrue("did not contain xml-declared bean", ctx.containsBean("xmlDeclaredBean"));
		ctx.close();
	}
