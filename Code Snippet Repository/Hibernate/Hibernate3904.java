	public Type getType() throws MappingException {
		if ( getColumnIterator().hasNext() ) {
			return getMetadata().getTypeResolver().getTypeFactory().specialOneToOne(
					getReferencedEntityName(), 
					foreignKeyType,
					referenceToPrimaryKey, 
					referencedPropertyName,
					isLazy(),
					isUnwrapProxy(),
					entityName,
					propertyName
			);
		}
		else {
			return getMetadata().getTypeResolver().getTypeFactory().oneToOne(
					getReferencedEntityName(), 
					foreignKeyType,
					referenceToPrimaryKey, 
					referencedPropertyName,
					isLazy(),
					isUnwrapProxy(),
					entityName,
					propertyName
			);
		}
	}
