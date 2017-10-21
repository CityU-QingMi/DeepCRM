	private void processAttributeConversions(XClass embeddableXClass, Map<String, AttributeConversionInfo> infoMap) {
		{
			// @Convert annotation on the Embeddable class level
			final Convert convertAnnotation = embeddableXClass.getAnnotation( Convert.class );
			if ( convertAnnotation != null ) {
				final AttributeConversionInfo info = new AttributeConversionInfo( convertAnnotation, embeddableXClass );
				if ( StringHelper.isEmpty( info.getAttributeName() ) ) {
					throw new IllegalStateException( "@Convert placed on @Embeddable must define attributeName" );
				}
				infoMap.put( info.getAttributeName(), info );
			}
		}
		{
			// @Converts annotation on the Embeddable class level
			final Converts convertsAnnotation = embeddableXClass.getAnnotation( Converts.class );
			if ( convertsAnnotation != null ) {
				for ( Convert convertAnnotation : convertsAnnotation.value() ) {
					final AttributeConversionInfo info = new AttributeConversionInfo( convertAnnotation, embeddableXClass );
					if ( StringHelper.isEmpty( info.getAttributeName() ) ) {
						throw new IllegalStateException( "@Converts placed on @Embeddable must define attributeName" );
					}
					infoMap.put( info.getAttributeName(), info );
				}
			}
		}
	}
