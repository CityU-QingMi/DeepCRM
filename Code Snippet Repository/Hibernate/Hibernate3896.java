	public CollectionType getDefaultCollectionType() {
		if ( isSorted() ) {
			return getMetadata().getTypeResolver()
					.getTypeFactory()
					.sortedMap( getRole(), getReferencedPropertyName(), getComparator() );
		}
		else if ( hasOrder() ) {
			return getMetadata().getTypeResolver()
					.getTypeFactory()
					.orderedMap( getRole(), getReferencedPropertyName() );
		}
		else {
			return getMetadata().getTypeResolver()
					.getTypeFactory()
					.map( getRole(), getReferencedPropertyName() );
		}
	}
