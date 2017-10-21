	@Test
	public void withEmptyResourceLocations() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ConfigWithEmptyResourceLocations.class);
		try {
			ctx.refresh();
		}
		catch (BeanDefinitionStoreException ex) {
			assertTrue(ex.getCause() instanceof IllegalArgumentException);
		}
	}
