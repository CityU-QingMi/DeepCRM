	@Test
	public void viaContextRegistration() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ComponentScanAnnotatedConfig.class);
		ctx.refresh();
		ctx.getBean(ComponentScanAnnotatedConfig.class);
		ctx.getBean(TestBean.class);
		assertThat("config class bean not found", ctx.containsBeanDefinition("componentScanAnnotatedConfig"), is(true));
		assertThat("@ComponentScan annotated @Configuration class registered directly against " +
				"AnnotationConfigApplicationContext did not trigger component scanning as expected",
				ctx.containsBean("fooServiceImpl"), is(true));
	}
