	@Test
	public void viaContextRegistration_WithValueAttribute() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ComponentScanAnnotatedConfig_WithValueAttribute.class);
		ctx.refresh();
		ctx.getBean(ComponentScanAnnotatedConfig_WithValueAttribute.class);
		ctx.getBean(TestBean.class);
		assertThat("config class bean not found", ctx.containsBeanDefinition("componentScanAnnotatedConfig_WithValueAttribute"), is(true));
		assertThat("@ComponentScan annotated @Configuration class registered directly against " +
				"AnnotationConfigApplicationContext did not trigger component scanning as expected",
				ctx.containsBean("fooServiceImpl"), is(true));
	}
