	private Property createAnyAssociationAttribute(
			MappingDocument sourceDocument,
			SingularAttributeSourceAny anyMapping,
			Any anyBinding,
			String entityName) {
		final String attributeName = anyMapping.getName();

		bindAny( sourceDocument, anyMapping, anyBinding, anyMapping.getAttributeRole(), anyMapping.getAttributePath() );

		prepareValueTypeViaReflection( sourceDocument, anyBinding, entityName, attributeName, anyMapping.getAttributeRole() );

		anyBinding.createForeignKey();

		Property prop = new Property();
		prop.setValue( anyBinding );
		bindProperty(
				sourceDocument,
				anyMapping,
				prop
		);

		return prop;
	}
