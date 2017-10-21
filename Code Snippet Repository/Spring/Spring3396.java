	@Test
	public void eventListenerWorksWithSimpleInterfaceProxy() throws Exception {
		load(ScopedProxyTestBean.class);

		SimpleService proxy = this.context.getBean(SimpleService.class);
		assertTrue("bean should be a proxy", proxy instanceof Advised);
		this.eventCollector.assertNoEventReceived(proxy.getId());

		this.context.publishEvent(new ContextRefreshedEvent(this.context));
		this.eventCollector.assertNoEventReceived(proxy.getId());

		TestEvent event = new TestEvent();
		this.context.publishEvent(event);
		this.eventCollector.assertEvent(proxy.getId(), event);
		this.eventCollector.assertTotalEventsCount(1);
	}
