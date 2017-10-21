	@Override
	@Nullable
	public <T> T get(Object key, Callable<T> valueLoader) {
		try {
			return this.cache.invoke(key, new ValueLoaderEntryProcessor<T>(), valueLoader);
		}
		catch (EntryProcessorException ex) {
			throw new ValueRetrievalException(key, valueLoader, ex.getCause());
		}
	}
