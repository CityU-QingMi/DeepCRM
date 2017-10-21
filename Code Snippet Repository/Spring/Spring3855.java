	@Test
	public void spr14949FindsOnInterfaceWithCglibProxy() throws InterruptedException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Spr14949ConfigB.class);

		AsyncInterface asyncBean = ctx.getBean(AsyncInterface.class);
		asyncBean.work();
		Thread.sleep(500);
		assertThat(asyncBean.getThreadOfExecution().getName(), startsWith("Custom-"));

		ctx.close();
	}
