	@Test
	public void exceptionPropagated() {
		load(ExceptionEventListener.class);
		TestEvent event = new TestEvent(this, "fail");
		ExceptionEventListener listener = this.context.getBean(ExceptionEventListener.class);
		this.eventCollector.assertNoEventReceived(listener);
		try {
			this.context.publishEvent(event);
			fail("An exception should have thrown");
		}
		catch (IllegalStateException e) {
			assertEquals("Wrong exception", "Test exception", e.getMessage());
			this.eventCollector.assertEvent(listener, event);
			this.eventCollector.assertTotalEventsCount(1);
		}
	}
