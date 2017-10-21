	@Deprecated
	public Object[] collectPositionalBindValues() {
		Object[] values = new Object[ positionalParameterBindings.size() ];

		// NOTE : bindings should be ordered by position by nature of a TreeMap...
		// NOTE : we also assume the contiguity of the positions

		for ( Map.Entry<Integer, QueryParameterBinding> entry : positionalParameterBindings.entrySet() ) {
			final int position = entry.getKey();
			values[ position ] = entry.getValue().getBindValue();
		}

		return values;
	}
