	@Test
	public void eventListenerWorksWithCglibProxy() throws Exception {
		load(CglibProxyTestBean.class);

		CglibProxyTestBean proxy = this.context.getBean(CglibProxyTestBean.class);
		assertTrue("bean should be a cglib proxy", AopUtils.isCglibProxy(proxy));
		this.eventCollector.assertNoEventReceived(proxy.getId());

		this.context.publishEvent(new ContextRefreshedEvent(this.context));
		this.eventCollector.assertNoEventReceived(proxy.getId());

		TestEvent event = new TestEvent();
		this.context.publishEvent(event);
		this.eventCollector.assertEvent(proxy.getId(), event);
		this.eventCollector.assertTotalEventsCount(1);
	}
