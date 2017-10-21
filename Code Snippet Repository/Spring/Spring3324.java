	@Test
	public void withUnresolvablePlaceholder() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ConfigWithUnresolvablePlaceholder.class);
		try {
			ctx.refresh();
		}
		catch (BeanDefinitionStoreException ex) {
			assertTrue(ex.getCause() instanceof IllegalArgumentException);
		}
	}
