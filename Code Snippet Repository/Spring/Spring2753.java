	@Test
	public void testAccessThrowable() throws Exception {
		ClassPathXmlApplicationContext ctx =
			new ClassPathXmlApplicationContext(getClass().getSimpleName() + "-context.xml", getClass());

		ITestBean bean = (ITestBean) ctx.getBean("testBean");
		ExceptionHandlingAspect aspect = (ExceptionHandlingAspect) ctx.getBean("aspect");

		assertTrue(AopUtils.isAopProxy(bean));
		try {
			bean.unreliableFileOperation();
		}
		catch (IOException e) {
			//
		}

		assertEquals(1, aspect.handled);
		assertNotNull(aspect.lastException);
	}
