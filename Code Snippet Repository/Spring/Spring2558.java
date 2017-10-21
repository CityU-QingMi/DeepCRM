	@Override
	@Nullable
	public V get() throws ExecutionException {
		if (this.executionException != null) {
			throw (this.executionException instanceof ExecutionException ?
					(ExecutionException) this.executionException :
					new ExecutionException(this.executionException));
		}
		return this.value;
	}
