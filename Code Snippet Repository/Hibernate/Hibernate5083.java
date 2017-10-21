	@Override
	public String toLoggableString(Object value, SessionFactoryImplementor factory) {
		if ( value == null ) {
			return "null";
		}

		final EntityPersister persister = getAssociatedEntityPersister( factory );
		if ( !persister.getEntityTuplizer().isInstance( value ) ) {
			// it should be the id type...
			if ( persister.getIdentifierType().getReturnedClass().isInstance( value ) ) {
				return associatedEntityName + "#" + value;
			}
		}

		final StringBuilder result = new StringBuilder().append( associatedEntityName );

		if ( persister.hasIdentifierProperty() ) {
			final Serializable id;
			if ( value instanceof HibernateProxy ) {
				HibernateProxy proxy = (HibernateProxy) value;
				id = proxy.getHibernateLazyInitializer().getIdentifier();
			}
			else {
				id = persister.getIdentifier( value );
			}

			result.append( '#' )
					.append( persister.getIdentifierType().toLoggableString( id, factory ) );
		}

		return result.toString();
	}
