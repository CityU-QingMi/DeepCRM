	@Test
	public void eventListenerWorksWithAnnotatedInterfaceProxy() throws Exception {
		load(AnnotatedProxyTestBean.class);

		AnnotatedSimpleService proxy = this.context.getBean(AnnotatedSimpleService.class);
		assertTrue("bean should be a proxy", proxy instanceof Advised);
		this.eventCollector.assertNoEventReceived(proxy.getId());

		this.context.publishEvent(new ContextRefreshedEvent(this.context));
		this.eventCollector.assertNoEventReceived(proxy.getId());

		TestEvent event = new TestEvent();
		this.context.publishEvent(event);
		this.eventCollector.assertEvent(proxy.getId(), event);
		this.eventCollector.assertTotalEventsCount(1);
	}
