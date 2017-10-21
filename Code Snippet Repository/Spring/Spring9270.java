	protected Mono<Void> doCommit(@Nullable Supplier<? extends Publisher<Void>> writeAction) {
		if (!this.state.compareAndSet(State.NEW, State.COMMITTING)) {
			return Mono.empty();
		}

		this.commitActions.add(() -> {
			applyHeaders();
			applyCookies();
			this.state.set(State.COMMITTED);
			return Mono.empty();
		});

		if (writeAction != null) {
			this.commitActions.add(writeAction);
		}

		List<? extends Publisher<Void>> actions = this.commitActions.stream()
				.map(Supplier::get).collect(Collectors.toList());

		return Mono.fromDirect(Flux.concat(actions));
	}
