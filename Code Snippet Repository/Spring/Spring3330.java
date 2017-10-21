	@Test
	public void onBeanMethod() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(Config.class);
		ctx.refresh();
		assertThat("Expected bean to have ROLE_APPLICATION",
				ctx.getBeanDefinition("foo").getRole(), is(BeanDefinition.ROLE_APPLICATION));
		assertThat(ctx.getBeanDefinition("foo").getDescription(), is((Object) null));
		assertThat("Expected bean to have ROLE_INFRASTRUCTURE",
				ctx.getBeanDefinition("bar").getRole(), is(BeanDefinition.ROLE_INFRASTRUCTURE));
		assertThat(ctx.getBeanDefinition("bar").getDescription(), is("A Bean method with a role"));
	}
