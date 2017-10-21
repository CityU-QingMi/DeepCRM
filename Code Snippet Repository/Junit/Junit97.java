	private Supplier<Object> getStoredValue(CompositeKey compositeKey) {
		Supplier<Object> storedValue = storedValues.get(compositeKey);
		if (storedValue != null) {
			return storedValue;
		}
		else if (parentStore != null) {
			return parentStore.getStoredValue(compositeKey);
		}
		else {
			return null;
		}
	}
