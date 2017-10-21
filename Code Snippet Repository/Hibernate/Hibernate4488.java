	@SuppressWarnings("")
	protected Iterator<R> doIterate() {
		if (getMaxResults() == 0){
			return EmptyIterator.INSTANCE;
		}
		return getProducer().iterate(
				queryParameterBindings.expandListValuedParameters( getQueryString(), getProducer() ),
				getQueryParameters()
		);
	}
