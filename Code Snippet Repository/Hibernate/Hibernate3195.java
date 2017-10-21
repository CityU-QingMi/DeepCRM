	public String toString(Map<String, TypedValue> namedTypedValues) throws HibernateException {
		Map<String, String> result = new HashMap<String, String>();
		for ( Map.Entry<String, TypedValue> entry : namedTypedValues.entrySet() ) {
			result.put(
					entry.getKey(), entry.getValue().getType().toLoggableString(
							entry.getValue().getValue(),
							factory
					)
			);
		}
		return result.toString();
	}
