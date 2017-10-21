	@Test
	public void deferredResultSubscriberWithOneValue() throws Exception {

		// Mono
		MonoProcessor<String> mono = MonoProcessor.create();
		testDeferredResultSubscriber(mono, Mono.class, forClass(String.class), () -> mono.onNext("foo"), "foo");

		// Mono empty
		MonoProcessor<String> monoEmpty = MonoProcessor.create();
		testDeferredResultSubscriber(monoEmpty, Mono.class, forClass(String.class), monoEmpty::onComplete, null);

		// RxJava 1 Single
		AtomicReference<SingleEmitter<String>> ref = new AtomicReference<>();
		Single<String> single = Single.fromEmitter(ref::set);
		testDeferredResultSubscriber(single, Single.class, forClass(String.class), () -> ref.get().onSuccess("foo"), "foo");

		// RxJava 2 Single
		AtomicReference<io.reactivex.SingleEmitter<String>> ref2 = new AtomicReference<>();
		io.reactivex.Single<String> single2 = io.reactivex.Single.create(ref2::set);
		testDeferredResultSubscriber(single2, io.reactivex.Single.class, forClass(String.class), () -> ref2.get().onSuccess("foo"), "foo");
	}
