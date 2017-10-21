	@Override
	public void addCallback(final SuccessCallback<? super T> successCallback, final FailureCallback failureCallback) {
		ListenableFuture<S> listenableAdaptee = (ListenableFuture<S>) getAdaptee();
		listenableAdaptee.addCallback(new ListenableFutureCallback<S>() {
			@Override
			public void onSuccess(@Nullable S result) {
				T adapted = null;
				if (result != null) {
					try {
						adapted = adaptInternal(result);
					}
					catch (ExecutionException ex) {
						Throwable cause = ex.getCause();
						onFailure(cause != null ? cause : ex);
						return;
					}
					catch (Throwable ex) {
						onFailure(ex);
						return;
					}
				}
				successCallback.onSuccess(adapted);
			}
			@Override
			public void onFailure(Throwable ex) {
				failureCallback.onFailure(ex);
			}
		});
	}
