	@Test
	public void viaContextRegistration_FromPackageOfConfigClass() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ComponentScanAnnotatedConfigWithImplicitBasePackage.class);
		ctx.refresh();
		ctx.getBean(ComponentScanAnnotatedConfigWithImplicitBasePackage.class);
		assertThat("config class bean not found", ctx.containsBeanDefinition("componentScanAnnotatedConfigWithImplicitBasePackage"), is(true));
		assertThat("@ComponentScan annotated @Configuration class registered directly against " +
				"AnnotationConfigApplicationContext did not trigger component scanning as expected",
				ctx.containsBean("scannedComponent"), is(true));
		assertThat("@Bean method overrides scanned class", ctx.getBean(ConfigurableComponent.class).isFlag(), is(true));
	}
