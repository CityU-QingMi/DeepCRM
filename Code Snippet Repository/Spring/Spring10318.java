	public void setParameters(Map<String, ?> params) {
		Assert.notNull(params, "Parameter map must not be null");
		for (String key : params.keySet()) {
			Object value = params.get(key);
			if (value instanceof String) {
				setParameter(key, (String) value);
			}
			else if (value instanceof String[]) {
				setParameter(key, (String[]) value);
			}
			else {
				throw new IllegalArgumentException(
						"Parameter map value must be single value " + " or array of type [" + String.class.getName() + "]");
			}
		}
	}
