	@Test
	public void defaultAdapterRegistrations() throws Exception {

		// Reactor
		assertNotNull(getAdapter(Mono.class));
		assertNotNull(getAdapter(Flux.class));

		// Publisher
		assertNotNull(getAdapter(Publisher.class));

		// Completable
		assertNotNull(getAdapter(CompletableFuture.class));

		// RxJava 1
		assertNotNull(getAdapter(Observable.class));
		assertNotNull(getAdapter(Single.class));
		assertNotNull(getAdapter(Completable.class));

		// RxJava 2
		assertNotNull(getAdapter(Flowable.class));
		assertNotNull(getAdapter(io.reactivex.Observable.class));
		assertNotNull(getAdapter(io.reactivex.Single.class));
		assertNotNull(getAdapter(Maybe.class));
		assertNotNull(getAdapter(io.reactivex.Completable.class));
	}
