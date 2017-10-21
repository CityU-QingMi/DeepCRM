	public CompletableToListenableFutureAdapter(CompletableFuture<T> completableFuture) {
		this.completableFuture = completableFuture;
		this.completableFuture.handle((result, ex) -> {
			if (ex != null) {
				callbacks.failure(ex);
			}
			else {
				callbacks.success(result);
			}
			return null;
		});
	}
