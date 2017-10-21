	@Test
	public void spr14949FindsOnInterfaceWithInterfaceProxy() throws InterruptedException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Spr14949ConfigA.class);

		AsyncInterface asyncBean = ctx.getBean(AsyncInterface.class);
		asyncBean.work();
		Thread.sleep(500);
		assertThat(asyncBean.getThreadOfExecution().getName(), startsWith("Custom-"));

		ctx.close();
	}
