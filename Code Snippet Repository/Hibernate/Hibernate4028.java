	private <X> Set<SingularAttribute<? super X, ?>> buildIdClassAttributes(
			AbstractIdentifiableType<X> ownerType,
			Iterator<Property> propertyIterator) {
		if ( LOG.isTraceEnabled() ) {
			LOG.trace( "Building old-school composite identifier [" + ownerType.getJavaType().getName() + ']' );
		}
		Set<SingularAttribute<? super X, ?>> attributes = new HashSet<SingularAttribute<? super X, ?>>();
		while ( propertyIterator.hasNext() ) {
			attributes.add( attributeFactory.buildIdAttribute( ownerType, propertyIterator.next() ) );
		}
		return attributes;
	}
