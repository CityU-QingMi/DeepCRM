	@Override
	public Serializable getIdentifier(Object entity, SharedSessionContractImplementor session) {
		final Object id;
		if ( entityMetamodel.getIdentifierProperty().isEmbedded() ) {
			id = entity;
		}
		else if ( HibernateProxy.class.isInstance( entity ) ) {
			id = ( (HibernateProxy) entity ).getHibernateLazyInitializer().getIdentifier();
		}
		else {
			if ( idGetter == null ) {
				if ( identifierMapperType == null ) {
					throw new HibernateException( "The class has no identifier property: " + getEntityName() );
				}
				else {
					id = mappedIdentifierValueMarshaller.getIdentifier( entity, getEntityMode(), session );
				}
			}
			else {
				id = idGetter.get( entity );
			}
		}

		try {
			return (Serializable) id;
		}
		catch (ClassCastException cce) {
			StringBuilder msg = new StringBuilder( "Identifier classes must be serializable. " );
			if ( id != null ) {
				msg.append( id.getClass().getName() ).append( " is not serializable. " );
			}
			if ( cce.getMessage() != null ) {
				msg.append( cce.getMessage() );
			}
			throw new ClassCastException( msg.toString() );
		}
	}
