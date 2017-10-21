	@Test
	public void withNonStaticBeanMethod() {
		MyBeanImpl.initialized = false;

		ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigWithNonStatic.class);
		MyBean bean = ctx.getBean("myBean", MyBean.class);

		assertFalse(MyBeanImpl.initialized);
		bean.doIt();
		assertTrue(MyBeanImpl.initialized);
	}
