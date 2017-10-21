	private void prepareValueTypeViaReflection(
			MappingDocument sourceDocument,
			Value value,
			String containingClassName,
			String propertyName,
			AttributeRole attributeRole) {
		if ( StringHelper.isEmpty( propertyName ) ) {
			throw new MappingException(
					String.format(
							Locale.ENGLISH,
							"Attribute mapping must define a name attribute: containingClassName=[%s], propertyName=[%s], role=[%s]",
							containingClassName,
							propertyName,
							attributeRole.getFullPath()
					),
					sourceDocument.getOrigin()
			);
		}

		try {
			value.setTypeUsingReflection( containingClassName, propertyName );
		}
		catch (org.hibernate.MappingException ome) {
			throw new MappingException(
					String.format(
							Locale.ENGLISH,
							"Error calling Value#setTypeUsingReflection: containingClassName=[%s], propertyName=[%s], role=[%s]",
							containingClassName,
							propertyName,
							attributeRole.getFullPath()
					),
					ome,
					sourceDocument.getOrigin()
			);
		}
	}
