	@Test
	public void getBeanByTypeRaisesNoSuchBeanDefinitionException() {
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

		// attempt to retrieve a bean that does not exist
		Class<?> targetType = Pattern.class;
		try {
			context.getBean(targetType);
			fail("Should have thrown NoSuchBeanDefinitionException");
		}
		catch (NoSuchBeanDefinitionException ex) {
			assertThat(ex.getMessage(), containsString(format("No qualifying bean of type '%s'", targetType.getName())));
		}
	}
