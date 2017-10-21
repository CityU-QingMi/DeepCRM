	@Override
	protected Object fromStoreValue(Object storeValue) {
		if (this.serialization != null) {
			try {
				return super.fromStoreValue(deserializeValue(this.serialization, storeValue));
			}
			catch (Throwable ex) {
				throw new IllegalArgumentException("Failed to deserialize cache value '" + storeValue + "'", ex);
			}
		}
		else {
			return super.fromStoreValue(storeValue);
		}

	}
