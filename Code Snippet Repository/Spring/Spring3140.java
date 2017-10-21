	@Test
	public void getBeanByTypeAmbiguityRaisesException() {
		ApplicationContext context = new AnnotationConfigApplicationContext(TwoTestBeanConfig.class);

		try {
			context.getBean(TestBean.class);
		}
		catch (NoSuchBeanDefinitionException ex) {
			assertThat(ex.getMessage(),
					allOf(
						containsString("No qualifying bean of type '" + TestBean.class.getName() + "'"),
						containsString("tb1"),
						containsString("tb2")
					)
				);
		}
	}
