	@Test
	public void anonymousClassAsListener() {
		final Set<MyEvent> seenEvents = new HashSet<>();
		StaticApplicationContext context = new StaticApplicationContext();
		context.addApplicationListener(new ApplicationListener<MyEvent>() {
			@Override
			public void onApplicationEvent(MyEvent event) {
				seenEvents.add(event);
			}
		});
		context.refresh();

		MyEvent event1 = new MyEvent(context);
		context.publishEvent(event1);
		context.publishEvent(new MyOtherEvent(context));
		MyEvent event2 = new MyEvent(context);
		context.publishEvent(event2);
		assertSame(2, seenEvents.size());
		assertTrue(seenEvents.contains(event1));
		assertTrue(seenEvents.contains(event2));

		context.close();
	}
