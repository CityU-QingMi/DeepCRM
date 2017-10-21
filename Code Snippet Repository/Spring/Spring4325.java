		void registerAdapters(ReactiveAdapterRegistry registry) {
			// Flux and Mono ahead of Publisher...

			registry.registerReactiveType(
					singleOptionalValue(Mono.class, Mono::empty),
					source -> (Mono<?>) source,
					Mono::from
			);

			registry.registerReactiveType(multiValue(Flux.class, Flux::empty),
					source -> (Flux<?>) source,
					Flux::from);

			registry.registerReactiveType(multiValue(Publisher.class, Flux::empty),
					source -> (Publisher<?>) source,
					source -> source);

			registry.registerReactiveType(
					singleOptionalValue(CompletableFuture.class, () -> {
						CompletableFuture<?> empty = new CompletableFuture<>();
						empty.complete(null);
						return empty;
					}),
					source -> Mono.fromFuture((CompletableFuture<?>) source),
					source -> Mono.from(source).toFuture()
			);
		}
