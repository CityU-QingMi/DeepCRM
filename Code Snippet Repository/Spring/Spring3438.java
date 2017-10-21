	@Test
	public void orderedListenersWithAnnotation() {
		MyOrderedListener3 listener1 = new MyOrderedListener3();
		MyOrderedListener4 listener2 = new MyOrderedListener4(listener1);

		SimpleApplicationEventMulticaster smc = new SimpleApplicationEventMulticaster();
		smc.addApplicationListener(listener2);
		smc.addApplicationListener(listener1);

		smc.multicastEvent(new MyEvent(this));
		smc.multicastEvent(new MyOtherEvent(this));
		assertEquals(2, listener1.seenEvents.size());
	}
