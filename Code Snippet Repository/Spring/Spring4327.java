		void registerAdapters(ReactiveAdapterRegistry registry) {
			registry.registerReactiveType(
					multiValue(io.reactivex.Flowable.class, io.reactivex.Flowable::empty),
					source -> (io.reactivex.Flowable<?>) source,
					source-> io.reactivex.Flowable.fromPublisher(source)
			);
			registry.registerReactiveType(
					multiValue(io.reactivex.Observable.class, io.reactivex.Observable::empty),
					source -> ((io.reactivex.Observable<?>) source).toFlowable(BackpressureStrategy.BUFFER),
					source -> io.reactivex.Flowable.fromPublisher(source).toObservable()
			);
			registry.registerReactiveType(
					singleRequiredValue(io.reactivex.Single.class),
					source -> ((io.reactivex.Single<?>) source).toFlowable(),
					source -> io.reactivex.Flowable.fromPublisher(source).toObservable().singleElement().toSingle()
			);
			registry.registerReactiveType(
					singleOptionalValue(io.reactivex.Maybe.class, io.reactivex.Maybe::empty),
					source -> ((io.reactivex.Maybe<?>) source).toFlowable(),
					source -> io.reactivex.Flowable.fromPublisher(source).toObservable().singleElement()
			);
			registry.registerReactiveType(
					noValue(io.reactivex.Completable.class, io.reactivex.Completable::complete),
					source -> ((io.reactivex.Completable) source).toFlowable(),
					source -> io.reactivex.Flowable.fromPublisher(source).toObservable().ignoreElements()
			);
		}
