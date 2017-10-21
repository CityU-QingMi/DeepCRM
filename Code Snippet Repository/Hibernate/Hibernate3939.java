	public CollectionType getDefaultCollectionType() {
		if ( isSorted() ) {
			return getMetadata().getTypeResolver()
					.getTypeFactory()
					.sortedSet( getRole(), getReferencedPropertyName(), getComparator() );
		}
		else if ( hasOrder() ) {
			return getMetadata().getTypeResolver()
					.getTypeFactory()
					.orderedSet( getRole(), getReferencedPropertyName() );
		}
		else {
			return getMetadata().getTypeResolver()
					.getTypeFactory()
					.set( getRole(), getReferencedPropertyName() );
		}
	}
