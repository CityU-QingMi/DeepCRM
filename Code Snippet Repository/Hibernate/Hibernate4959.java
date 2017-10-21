	@Override
	public void setIdentifier(Object entity, Serializable id, SharedSessionContractImplementor session) {
		if ( entityMetamodel.getIdentifierProperty().isEmbedded() ) {
			if ( entity != id ) {
				CompositeType copier = (CompositeType) entityMetamodel.getIdentifierProperty().getType();
				copier.setPropertyValues( entity, copier.getPropertyValues( id, getEntityMode() ), getEntityMode() );
			}
		}
		else if ( idSetter != null ) {
			idSetter.set( entity, id, getFactory() );
		}
		else if ( identifierMapperType != null ) {
			mappedIdentifierValueMarshaller.setIdentifier( entity, id, getEntityMode(), session );
		}
	}
