	@SuppressWarnings("")
	private void handle(
			@Validated Mono<TestBean> monoTestBean,
			@Validated Flux<TestBean> fluxTestBean,
			Single<TestBean> singleTestBean,
			io.reactivex.Single<TestBean> rxJava2SingleTestBean,
			Maybe<TestBean> rxJava2MaybeTestBean,
			Observable<TestBean> observableTestBean,
			io.reactivex.Observable<TestBean> rxJava2ObservableTestBean,
			Flowable<TestBean> flowableTestBean,
			CompletableFuture<TestBean> futureTestBean,
			TestBean testBean,
			Map<String, String> map,
			List<TestBean> list,
			Mono<List<TestBean>> monoList,
			Set<TestBean> set,
			TestBean[] array) {
	}
