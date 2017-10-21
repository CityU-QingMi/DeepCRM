	@Test
	public void eventListenerWorksWithCustomScope() throws Exception {
		load(CustomScopeTestBean.class);
		CustomScope customScope = new CustomScope();
		this.context.getBeanFactory().registerScope("custom", customScope);

		CustomScopeTestBean proxy = this.context.getBean(CustomScopeTestBean.class);
		assertTrue("bean should be a cglib proxy", AopUtils.isCglibProxy(proxy));
		this.eventCollector.assertNoEventReceived(proxy.getId());

		this.context.publishEvent(new ContextRefreshedEvent(this.context));
		this.eventCollector.assertNoEventReceived(proxy.getId());

		customScope.active = false;
		this.context.publishEvent(new ContextRefreshedEvent(this.context));
		customScope.active = true;
		this.eventCollector.assertNoEventReceived(proxy.getId());

		TestEvent event = new TestEvent();
		this.context.publishEvent(event);
		this.eventCollector.assertEvent(proxy.getId(), event);
		this.eventCollector.assertTotalEventsCount(1);

		try {
			customScope.active = false;
			this.context.publishEvent(new TestEvent());
			fail("Should have thrown IllegalStateException");
		}
		catch (BeanCreationException ex) {
			// expected
			assertTrue(ex.getCause() instanceof IllegalStateException);
		}
	}
