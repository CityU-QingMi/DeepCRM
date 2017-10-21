	private void checkForOrphanProperties(Element tree) {
		Class clazz;
		try {
			clazz = classLoaderAccess.classForName( className );
		}
		catch ( ClassLoadingException e ) {
			return; //a primitive type most likely
		}
		Element element = tree != null ? tree.element( "attributes" ) : null;
		//put entity.attributes elements
		if ( element != null ) {
			//precompute the list of properties
			//TODO is it really useful...
			Set<String> properties = new HashSet<String>();
			for ( Field field : clazz.getFields() ) {
				properties.add( field.getName() );
			}
			for ( Method method : clazz.getMethods() ) {
				String name = method.getName();
				if ( name.startsWith( "get" ) ) {
					properties.add( Introspector.decapitalize( name.substring( "get".length() ) ) );
				}
				else if ( name.startsWith( "is" ) ) {
					properties.add( Introspector.decapitalize( name.substring( "is".length() ) ) );
				}
			}
			for ( Element subelement : (List<Element>) element.elements() ) {
				String propertyName = subelement.attributeValue( "name" );
				if ( !properties.contains( propertyName ) ) {
					LOG.propertyNotFound( StringHelper.qualify( className, propertyName ) );
				}
			}
		}
	}
