	@Test
	public void registerOuterConfig_withBeanNameGenerator() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.setBeanNameGenerator(new AnnotationBeanNameGenerator() {
			@Override
			public String generateBeanName(
					BeanDefinition definition, BeanDefinitionRegistry registry) {
				return "custom-" + super.generateBeanName(definition, registry);
			}
		});
		ctx.register(A.class);
		ctx.refresh();
		assertThat(ctx.containsBean("custom-outer"), is(true));
		assertThat(ctx.containsBean("custom-imported"), is(true));
		assertThat(ctx.containsBean("custom-nested"), is(true));
		assertThat(ctx.containsBean("nestedBean"), is(true));
	}
