	@Test
	public void customExecutorConfig() throws InterruptedException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(CustomExecutorConfig.class);
		ctx.refresh();

		AsyncBean asyncBean = ctx.getBean(AsyncBean.class);
		asyncBean.work();
		Thread.sleep(500);
		assertThat(asyncBean.getThreadOfExecution().getName(), startsWith("Custom-"));

		TestableAsyncUncaughtExceptionHandler exceptionHandler = (TestableAsyncUncaughtExceptionHandler)
				ctx.getBean("exceptionHandler");
		assertFalse("handler should not have been called yet", exceptionHandler.isCalled());

		asyncBean.fail();
		Thread.sleep(500);
		Method method = ReflectionUtils.findMethod(AsyncBean.class, "fail");
		exceptionHandler.assertCalledWith(method, UnsupportedOperationException.class);

		ctx.close();
	}
