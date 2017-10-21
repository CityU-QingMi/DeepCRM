	protected QueryParameterBinding locateAndRemoveBinding(String name) {
		final Iterator<Map.Entry<QueryParameter, QueryParameterBinding>> entryIterator = parameterBindingMap.entrySet().iterator();
		while ( entryIterator.hasNext() ) {
			final Map.Entry<QueryParameter, QueryParameterBinding> entry = entryIterator.next();
			if ( name.equals( entry.getKey().getName() ) ) {
				entryIterator.remove();
				return entry.getValue();
			}
		}

		return null;
	}
