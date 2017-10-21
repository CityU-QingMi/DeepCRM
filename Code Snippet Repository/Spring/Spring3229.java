	@Test
	public void viaContextRegistration_WithComposedAnnotation() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ComposedAnnotationConfig.class);
		ctx.refresh();
		ctx.getBean(ComposedAnnotationConfig.class);
		ctx.getBean(SimpleComponent.class);
		ctx.getBean(ClassWithNestedComponents.NestedComponent.class);
		ctx.getBean(ClassWithNestedComponents.OtherNestedComponent.class);
		assertThat("config class bean not found",
				ctx.containsBeanDefinition("componentScanAnnotationIntegrationTests.ComposedAnnotationConfig"), is(true));
		assertThat("@ComponentScan annotated @Configuration class registered directly against " +
						"AnnotationConfigApplicationContext did not trigger component scanning as expected",
				ctx.containsBean("simpleComponent"), is(true));
	}
