	private Property makePropertyAndValue() {
		validateBind();

		LOG.debugf( "MetadataSourceProcessor property %s with lazy=%s", name, lazy );
		final String containerClassName = holder.getClassName();
		holder.startingProperty( property );

		simpleValueBinder = new SimpleValueBinder();
		simpleValueBinder.setBuildingContext( buildingContext );
		simpleValueBinder.setPropertyName( name );
		simpleValueBinder.setReturnedClassName( returnedClassName );
		simpleValueBinder.setColumns( columns );
		simpleValueBinder.setPersistentClassName( containerClassName );
		simpleValueBinder.setType(
				property,
				returnedClass,
				containerClassName,
				holder.resolveAttributeConverterDescriptor( property )
		);
		simpleValueBinder.setReferencedEntityName( referencedEntityName );
		simpleValueBinder.setAccessType( accessType );
		SimpleValue propertyValue = simpleValueBinder.make();
		setValue( propertyValue );
		return makeProperty();
	}
