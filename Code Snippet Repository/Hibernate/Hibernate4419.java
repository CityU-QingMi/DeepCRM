	@Override
	public FromImplementor<Z, X> getCorrelationParent() {
		if ( correlationParent == null ) {
			throw new IllegalStateException(
					String.format(
							"Criteria query From node [%s] is not part of a subquery correlation",
							getPathIdentifier()
					)
			);
		}
		return correlationParent;
	}
