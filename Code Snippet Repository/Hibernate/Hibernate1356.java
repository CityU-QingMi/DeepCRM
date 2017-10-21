	@Override
	public boolean isInPrimaryKey() {
		if ( entityClassName == null ) return false;
		final PersistentClass persistentClass = buildingContext.getMetadataCollector().getEntityBinding( entityClassName );
		Property property = persistentClass.getIdentifierProperty();
		if ( path == null ) {
			return false;
		}
		else if ( property != null) {
			//try explicit identifier property
			return path.startsWith( property.getName() + "." );
		}
		else {
			//try the embedded property
			//embedded property starts their path with 'id.' See PropertyPreloadedData( ) use when idClass != null in AnnotationSourceProcessor
			if ( path.startsWith( "id." ) ) {
				KeyValue valueIdentifier = persistentClass.getIdentifier();
				String localPath = path.substring( 3 );
				if ( valueIdentifier instanceof Component ) {
					Iterator it = ( (Component) valueIdentifier ).getPropertyIterator();
					while ( it.hasNext() ) {
						Property idProperty = (Property) it.next();
						if ( localPath.startsWith( idProperty.getName() ) ) return true;
					}

				}
			}
		}
		return false;
	}
