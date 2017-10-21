	@Test
	@SuppressWarnings("")
	public void proxiedListenersMixedWithTargetListeners() {
		MyOrderedListener1 listener1 = new MyOrderedListener1();
		MyOrderedListener2 listener2 = new MyOrderedListener2(listener1);
		ApplicationListener<ApplicationEvent> proxy1 = (ApplicationListener<ApplicationEvent>) new ProxyFactory(listener1).getProxy();
		ApplicationListener<ApplicationEvent> proxy2 = (ApplicationListener<ApplicationEvent>) new ProxyFactory(listener2).getProxy();

		SimpleApplicationEventMulticaster smc = new SimpleApplicationEventMulticaster();
		smc.addApplicationListener(listener1);
		smc.addApplicationListener(listener2);
		smc.addApplicationListener(proxy1);
		smc.addApplicationListener(proxy2);

		smc.multicastEvent(new MyEvent(this));
		smc.multicastEvent(new MyOtherEvent(this));
		assertEquals(2, listener1.seenEvents.size());
	}
