	@Test
	public void proxyingOccursWithMockitoStub() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AsyncConfigWithMockito.class, AsyncBeanUser.class);
		ctx.refresh();

		AsyncBeanUser asyncBeanUser = ctx.getBean(AsyncBeanUser.class);
		AsyncBean asyncBean = asyncBeanUser.getAsyncBean();
		assertThat(AopUtils.isAopProxy(asyncBean), is(true));
		asyncBean.work();
	}
