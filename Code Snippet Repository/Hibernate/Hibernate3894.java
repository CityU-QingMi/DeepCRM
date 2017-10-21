	public Type getType() throws MappingException {
		return getMetadata().getTypeResolver().getTypeFactory().manyToOne(
				getReferencedEntityName(),
				referenceToPrimaryKey, 
				getReferencedPropertyName(),
				isLazy(),
				isUnwrapProxy(),
				isIgnoreNotFound(),
				isLogicalOneToOne
		);
	}
