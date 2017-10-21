	@Test
	public void deferredResultSubscriberWithMultipleValues() throws Exception {

		// JSON must be preferred for Flux<String> -> List<String> or else we stream
		this.servletRequest.addHeader("Accept", "application/json");

		Bar bar1 = new Bar("foo");
		Bar bar2 = new Bar("bar");

		EmitterProcessor<Bar> emitter = EmitterProcessor.create();
		testDeferredResultSubscriber(emitter, Flux.class, forClass(Bar.class), () -> {
			emitter.onNext(bar1);
			emitter.onNext(bar2);
			emitter.onComplete();
		}, Arrays.asList(bar1, bar2));
	}
