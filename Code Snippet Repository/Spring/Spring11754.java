	@Test
	public void voidReturnType() throws Exception {
		testVoid(null, on(TestController.class).resolveReturnType(void.class));
		testVoid(Mono.empty(), on(TestController.class).resolveReturnType(Mono.class, Void.class));
		testVoid(Flux.empty(), on(TestController.class).resolveReturnType(Flux.class, Void.class));
		testVoid(Completable.complete(), on(TestController.class).resolveReturnType(Completable.class));
		testVoid(Observable.empty(), on(TestController.class).resolveReturnType(Observable.class, Void.class));

		MethodParameter type = on(TestController.class).resolveReturnType(io.reactivex.Completable.class);
		testVoid(io.reactivex.Completable.complete(), type);

		type = on(TestController.class).resolveReturnType(io.reactivex.Observable.class, Void.class);
		testVoid(io.reactivex.Observable.empty(), type);

		type = on(TestController.class).resolveReturnType(Flowable.class, Void.class);
		testVoid(Flowable.empty(), type);
	}
