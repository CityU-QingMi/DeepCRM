	@Test
	public void supports() throws Exception {
		testSupports(this.testMethod.arg(httpEntityType(String.class)));
		testSupports(this.testMethod.arg(httpEntityType(Mono.class, String.class)));
		testSupports(this.testMethod.arg(httpEntityType(Single.class, String.class)));
		testSupports(this.testMethod.arg(httpEntityType(io.reactivex.Single.class, String.class)));
		testSupports(this.testMethod.arg(httpEntityType(Maybe.class, String.class)));
		testSupports(this.testMethod.arg(httpEntityType(CompletableFuture.class, String.class)));
		testSupports(this.testMethod.arg(httpEntityType(Flux.class, String.class)));
		testSupports(this.testMethod.arg(httpEntityType(Observable.class, String.class)));
		testSupports(this.testMethod.arg(httpEntityType(io.reactivex.Observable.class, String.class)));
		testSupports(this.testMethod.arg(httpEntityType(Flowable.class, String.class)));
		testSupports(this.testMethod.arg(forClassWithGenerics(RequestEntity.class, String.class)));
	}
