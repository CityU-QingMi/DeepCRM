	private void categorizeAnnotatedClass(Class annotatedClass, AttributeConverterManager attributeConverterManager) {
		final XClass xClass = reflectionManager.toXClass( annotatedClass );
		// categorize it, based on assumption it does not fall into multiple categories
		if ( xClass.isAnnotationPresent( Converter.class ) ) {
			//noinspection unchecked
			attributeConverterManager.addAttributeConverter( annotatedClass );
		}
		else if ( xClass.isAnnotationPresent( Entity.class )
				|| xClass.isAnnotationPresent( MappedSuperclass.class ) ) {
			xClasses.add( xClass );
		}
		else if ( xClass.isAnnotationPresent( Embeddable.class ) ) {
			xClasses.add( xClass );
		}
		else {
			log.debugf( "Encountered a non-categorized annotated class [%s]; ignoring", annotatedClass.getName() );
		}
	}
