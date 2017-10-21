	private Property createOneToOneAttribute(
			MappingDocument sourceDocument,
			SingularAttributeSourceOneToOne oneToOneSource,
			OneToOne oneToOneBinding,
			String containingClassName) {
		bindOneToOne( sourceDocument, oneToOneSource, oneToOneBinding );

		prepareValueTypeViaReflection(
				sourceDocument,
				oneToOneBinding,
				containingClassName,
				oneToOneSource.getName(),
				oneToOneSource.getAttributeRole()
		);

		final String propertyRef = oneToOneBinding.getReferencedPropertyName();
		if ( propertyRef != null ) {
			handlePropertyReference(
					sourceDocument,
					oneToOneBinding.getReferencedEntityName(),
					propertyRef,
					true,
					"<one-to-one name=\"" + oneToOneSource.getName() + "\"/>"
			);
		}

		oneToOneBinding.createForeignKey();

		Property prop = new Property();
		prop.setValue( oneToOneBinding );
		bindProperty(
				sourceDocument,
				oneToOneSource,
				prop
		);

		return prop;
	}
