	public Set<SingularAttribute<? super X, ?>> getIdClassAttributesSafely() {
		if ( !hasIdClass() ) {
			return null;
		}
		final Set<SingularAttribute<? super X, ?>> attributes = new HashSet<SingularAttribute<? super X, ?>>();
		internalCollectIdClassAttributes( attributes );

		if ( attributes.isEmpty() ) {
			return null;
		}

		return attributes;
	}
