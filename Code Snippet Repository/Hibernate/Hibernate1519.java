	private void addClass(List<Element> entities, String packageName, Default defaults, List<String> addedClasses) {
		for (Element element : entities) {
			String className = buildSafeClassName( element.attributeValue( "class" ), packageName );
			if ( classOverriding.containsKey( className ) ) {
				//maybe switch it to warn?
				throw new IllegalStateException( "Duplicate XML entry for " + className );
			}
			addedClasses.add( className );
			classOverriding.put( className, element );
			Default localDefault = new Default();
			localDefault.override( defaults );
			String metadataCompleteString = element.attributeValue( "metadata-complete" );
			if ( metadataCompleteString != null ) {
				localDefault.setMetadataComplete( Boolean.parseBoolean( metadataCompleteString ) );
			}
			String access = element.attributeValue( "access" );
			setAccess( access, localDefault );
			defaultsOverriding.put( className, localDefault );

			LOG.debugf( "Adding XML overriding information for %s", className );
			addEntityListenerClasses( element, packageName, addedClasses );
		}
	}
