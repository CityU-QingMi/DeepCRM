	@Test
	public void withNonStaticBeanMethodAndInterface() {
		MyBeanImpl.initialized = false;

		ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigWithNonStaticAndInterface.class);
		MyBean bean = ctx.getBean("myBean", MyBean.class);

		assertFalse(MyBeanImpl.initialized);
		bean.doIt();
		assertTrue(MyBeanImpl.initialized);
	}
