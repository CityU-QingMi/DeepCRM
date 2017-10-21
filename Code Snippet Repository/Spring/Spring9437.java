	protected Mono<Void> doCommit(@Nullable Supplier<? extends Mono<Void>> writeAction) {
		if (!this.state.compareAndSet(State.NEW, State.COMMITTING)) {
			if (logger.isDebugEnabled()) {
				logger.debug("Skipping doCommit (response already committed).");
			}
			return Mono.empty();
		}

		this.commitActions.add(() -> {
			applyStatusCode();
			applyHeaders();
			applyCookies();
			this.state.set(State.COMMITTED);
			return Mono.empty();
		});

		if (writeAction != null) {
			this.commitActions.add(writeAction);
		}

		List<? extends Mono<Void>> actions = this.commitActions.stream()
				.map(Supplier::get).collect(Collectors.toList());

		return Flux.concat(actions).next();
	}
