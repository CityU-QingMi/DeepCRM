		@SuppressWarnings("")
		@Override
		@Nullable
		public T process(MutableEntry<Object, Object> entry, Object... arguments) throws EntryProcessorException {
			Callable<T> valueLoader = (Callable<T>) arguments[0];
			if (entry.exists()) {
				return (T) fromStoreValue(entry.getValue());
			}
			else {
				T value;
				try {
					value = valueLoader.call();
				}
				catch (Exception ex) {
					throw new EntryProcessorException("Value loader '" + valueLoader + "' failed " +
							"to compute  value for key '" + entry.getKey() + "'", ex);
				}
				entry.setValue(toStoreValue(value));
				return value;
			}
		}
