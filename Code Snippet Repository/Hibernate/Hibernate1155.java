	public NaturalIdCacheKey(
			final Object[] naturalIdValues,
			Type[] propertyTypes, int[] naturalIdPropertyIndexes, final String entityName,
			final SharedSessionContractImplementor session) {

		this.entityName = entityName;
		this.tenantId = session.getTenantIdentifier();

		this.naturalIdValues = new Serializable[naturalIdValues.length];

		final SessionFactoryImplementor factory = session.getFactory();

		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( this.entityName == null ) ? 0 : this.entityName.hashCode() );
		result = prime * result + ( ( this.tenantId == null ) ? 0 : this.tenantId.hashCode() );
		for ( int i = 0; i < naturalIdValues.length; i++ ) {
			final int naturalIdPropertyIndex = naturalIdPropertyIndexes[i];
			final Type type = propertyTypes[naturalIdPropertyIndex];
			final Object value = naturalIdValues[i];

			result = prime * result + (value != null ? type.getHashCode( value, factory ) : 0);

			// The natural id may not be fully resolved in some situations.  See HHH-7513 for one of them
			// (re-attaching a mutable natural id uses a database snapshot and hydration does not resolve associations).
			// TODO: The snapshot should probably be revisited at some point.  Consider semi-resolving, hydrating, etc.
			if (type instanceof EntityType && type.getSemiResolvedType( factory ).getReturnedClass().isInstance( value )) {
				this.naturalIdValues[i] = (Serializable) value;
			}
			else {
				this.naturalIdValues[i] = type.disassemble( value, session, null );
			}
		}

		this.hashCode = result;
		initTransients();
	}
