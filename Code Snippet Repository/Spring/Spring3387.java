	@Test
	public void test() {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);

		assertThat("someDependency was not post processed",
				ctx.getBeanFactory().getBeanDefinition("someDependency").getDescription(),
				equalTo("post processed by MyPostProcessor"));

		MyConfig config = ctx.getBean(MyConfig.class);
		assertTrue("Config class was not enhanced", ClassUtils.isCglibProxy(config));
	}
