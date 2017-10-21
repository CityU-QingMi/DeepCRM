	private void collectAttributeConversionInfo(Map<String, AttributeConversionInfo> infoMap, XClass xClass) {
		if ( xClass == null ) {
			// typically indicates we have reached the end of the inheritance hierarchy
			return;
		}

		// collect superclass info first
		collectAttributeConversionInfo( infoMap, xClass.getSuperclass() );

		final boolean canContainConvert = xClass.isAnnotationPresent( javax.persistence.Entity.class )
				|| xClass.isAnnotationPresent( javax.persistence.MappedSuperclass.class )
				|| xClass.isAnnotationPresent( javax.persistence.Embeddable.class );
		if ( ! canContainConvert ) {
			return;
		}

		{
			final Convert convertAnnotation = xClass.getAnnotation( Convert.class );
			if ( convertAnnotation != null ) {
				final AttributeConversionInfo info = new AttributeConversionInfo( convertAnnotation, xClass );
				if ( StringHelper.isEmpty( info.getAttributeName() ) ) {
					throw new IllegalStateException( "@Convert placed on @Entity/@MappedSuperclass must define attributeName" );
				}
				infoMap.put( info.getAttributeName(), info );
			}
		}
		{
			final Converts convertsAnnotation = xClass.getAnnotation( Converts.class );
			if ( convertsAnnotation != null ) {
				for ( Convert convertAnnotation : convertsAnnotation.value() ) {
					final AttributeConversionInfo info = new AttributeConversionInfo( convertAnnotation, xClass );
					if ( StringHelper.isEmpty( info.getAttributeName() ) ) {
						throw new IllegalStateException( "@Converts placed on @Entity/@MappedSuperclass must define attributeName" );
					}
					infoMap.put( info.getAttributeName(), info );
				}
			}
		}
	}
