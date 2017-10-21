	@Test
	public void withStaticBeanMethod() {
		MyBeanImpl.initialized = false;

		ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigWithStatic.class);
		MyBean bean = ctx.getBean("myBean", MyBean.class);

		assertFalse(MyBeanImpl.initialized);
		bean.doIt();
		assertTrue(MyBeanImpl.initialized);
	}
