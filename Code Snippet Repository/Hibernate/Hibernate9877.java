	@SuppressWarnings({""})
	private boolean addIdProperties(
			Element parent,
			Iterator<Property> properties,
			SimpleMapperBuilder mapper,
			boolean key,
			boolean audited) {
		while ( properties.hasNext() ) {
			final Property property = properties.next();
			final Type propertyType = property.getType();
			if ( !"_identifierMapper".equals( property.getName() ) ) {
				boolean added = false;
				if ( propertyType instanceof ManyToOneType ) {
					added = mainGenerator.getBasicMetadataGenerator().addManyToOne(
							parent,
							getIdPersistentPropertyAuditingData( property ),
							property.getValue(),
							mapper
					);
				}
				else {
					// Last but one parameter: ids are always insertable
					added = mainGenerator.getBasicMetadataGenerator().addBasic(
							parent,
							getIdPersistentPropertyAuditingData( property ),
							property.getValue(),
							mapper,
							true,
							key
					);
				}
				if ( !added ) {
					// If the entity is audited, and a non-supported id component is used, throwing an exception.
					// If the entity is not audited, then we simply don't support this entity, even in
					// target relation mode not audited.
					if ( audited ) {
						throw new MappingException( "Type not supported: " + propertyType.getClass().getName() );
					}
					else {
						return false;
					}
				}
			}
		}

		return true;
	}
