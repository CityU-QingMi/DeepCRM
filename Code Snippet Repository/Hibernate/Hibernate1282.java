	private Property createComponentProperty(
			PersistentClass referencedPersistentClass,
			boolean isExplicitReference,
			Map<String, Ejb3JoinColumn> columnByReferencedName,
			AtomicInteger index,
			Property referencedProperty ) {
		Property property = new Property();
		property.setName( referencedProperty.getName() );
		//FIXME set optional?
		//property.setOptional( property.isOptional() );
		property.setPersistentClass( component.getOwner() );
		property.setPropertyAccessorName( referencedProperty.getPropertyAccessorName() );
		Component value = new Component( buildingContext.getMetadataCollector(), component.getOwner() );

		property.setValue( value );
		final Component referencedValue = (Component) referencedProperty.getValue();
		value.setTypeName( referencedValue.getTypeName() );
		value.setTypeParameters( referencedValue.getTypeParameters() );
		value.setComponentClassName( referencedValue.getComponentClassName() );


		Iterator<Property> propertyIterator = referencedValue.getPropertyIterator();
		while(propertyIterator.hasNext()) {
			Property referencedComponentProperty = propertyIterator.next();

			if ( referencedComponentProperty.isComposite() ) {
				Property componentProperty = createComponentProperty( referencedValue.getOwner(), isExplicitReference, columnByReferencedName, index, referencedComponentProperty );
				value.addProperty( componentProperty );
			}
			else {
				Property componentProperty = createSimpleProperty( referencedValue.getOwner(), isExplicitReference, columnByReferencedName, index, referencedComponentProperty );
				value.addProperty( componentProperty );
			}
		}

		return property;
	}
