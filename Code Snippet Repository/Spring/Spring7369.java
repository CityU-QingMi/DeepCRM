	@Override
	public ListenableFuture<Void> shutdown() {
		if (this.stopping) {
			SettableListenableFuture<Void> future = new SettableListenableFuture<>();
			future.set(null);
			return future;
		}

		this.stopping = true;

		ChannelGroupFuture close = this.channelGroup.close();
		Mono<Void> completion = FutureMono.from(close)
				.doOnSuccessOrError((x, e) -> {
					// TODO: https://github.com/reactor/reactor-netty/issues/24
					shutdownGlobalResources();

					this.loopResources.dispose();
					this.poolResources.dispose();

					// TODO: https://github.com/reactor/reactor-netty/issues/25
					try {
						Thread.sleep(2000);
					}
					catch (InterruptedException ex) {
						ex.printStackTrace();
					}

					// Scheduler after loop resources...
					this.scheduler.dispose();
				});

		return new MonoToListenableFutureAdapter<>(completion);
	}
