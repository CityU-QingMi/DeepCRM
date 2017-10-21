	@Test
	public void deferredResultSubscriberWithError() throws Exception {

		IllegalStateException ex = new IllegalStateException();

		// Mono
		MonoProcessor<String> mono = MonoProcessor.create();
		testDeferredResultSubscriber(mono, Mono.class, forClass(String.class), () -> mono.onError(ex), ex);

		// RxJava 1 Single
		AtomicReference<SingleEmitter<String>> ref = new AtomicReference<>();
		Single<String> single = Single.fromEmitter(ref::set);
		testDeferredResultSubscriber(single, Single.class, forClass(String.class), () -> ref.get().onError(ex), ex);

		// RxJava 2 Single
		AtomicReference<io.reactivex.SingleEmitter<String>> ref2 = new AtomicReference<>();
		io.reactivex.Single<String> single2 = io.reactivex.Single.create(ref2::set);
		testDeferredResultSubscriber(single2, io.reactivex.Single.class, forClass(String.class), () -> ref2.get().onError(ex), ex);
	}
