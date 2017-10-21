	public Type getPropertyType(String propertyName, String propertyPath) {
		checkInitialized();
		Type type = null;
		// If this is an entity and the property is the identifier property, then use getIdentifierType().
		//      Note that the propertyName.equals( getPropertyPath ) checks whether we have a component
		//      key reference, where the component class property name is the same as the
		//      entity id property name; if the two are not equal, this is the case and
		//      we'd need to "fall through" to using the property mapping.
		if ( persister != null && propertyName.equals( propertyPath ) && propertyName.equals( persister.getIdentifierPropertyName() ) ) {
			type = persister.getIdentifierType();
		}
		else {    // Otherwise, use the property mapping.
			PropertyMapping mapping = getPropertyMapping( propertyName );
			type = mapping.toType( propertyPath );
		}
		if ( type == null ) {
			throw new MappingException(
					"Property " + propertyName + " does not exist in " +
							( ( queryableCollection == null ) ? "class" : "collection" ) + " "
							+ ( ( queryableCollection == null ) ?
							fromElement.getClassName() :
							queryableCollection.getRole() )
			);
		}
		return type;
	}
