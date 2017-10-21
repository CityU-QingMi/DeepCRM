	@Test
	public void onComponentClass() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ComponentWithoutRole.class, ComponentWithRole.class);
		ctx.refresh();
		assertThat("Expected bean to have ROLE_APPLICATION",
				ctx.getBeanDefinition("componentWithoutRole").getRole(), is(BeanDefinition.ROLE_APPLICATION));
		assertThat(ctx.getBeanDefinition("componentWithoutRole").getDescription(), is((Object) null));
		assertThat("Expected bean to have ROLE_INFRASTRUCTURE",
				ctx.getBeanDefinition("componentWithRole").getRole(), is(BeanDefinition.ROLE_INFRASTRUCTURE));
		assertThat(ctx.getBeanDefinition("componentWithRole").getDescription(), is("A Component with a role"));
	}
