	@Test
	public void customExecutorBean() throws InterruptedException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(CustomExecutorBean.class);
		ctx.refresh();

		AsyncBean asyncBean = ctx.getBean(AsyncBean.class);
		asyncBean.work();
		Thread.sleep(500);
		assertThat(asyncBean.getThreadOfExecution().getName(), startsWith("Custom-"));

		ctx.close();
	}
