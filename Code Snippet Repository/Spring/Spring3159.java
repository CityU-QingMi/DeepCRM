	@Test
	public void withStaticBeanMethodAndInterface() {
		MyBeanImpl.initialized = false;

		ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigWithStaticAndInterface.class);
		MyBean bean = ctx.getBean("myBean", MyBean.class);

		assertFalse(MyBeanImpl.initialized);
		bean.doIt();
		assertTrue(MyBeanImpl.initialized);
	}
