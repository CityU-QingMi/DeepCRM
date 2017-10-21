	@Override
	@SuppressWarnings({""})
	public <X> ManagedType<X> managedType(Class<X> cls) {
		ManagedType<?> type = jpaEntityTypeMap.get( cls );
		if ( type == null ) {
			type = jpaMappedSuperclassTypeMap.get( cls );
		}
		if ( type == null ) {
			type = jpaEmbeddableTypeMap.get( cls );
		}
		if ( type == null ) {
			throw new IllegalArgumentException( "Not a managed type: " + cls );
		}
		return (ManagedType<X>) type;
	}
