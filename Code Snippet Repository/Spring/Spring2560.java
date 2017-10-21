	@Override
	public CompletableFuture<V> completable() {
		if (this.executionException != null) {
			CompletableFuture<V> completable = new CompletableFuture<>();
			completable.completeExceptionally(exposedException(this.executionException));
			return completable;
		}
		else {
			return CompletableFuture.completedFuture(this.value);
		}
	}
