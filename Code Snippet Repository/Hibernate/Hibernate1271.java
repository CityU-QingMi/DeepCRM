	private Map<String,AttributeConversionInfo> processAttributeConversions(XProperty embeddedXProperty) {
		final Map<String,AttributeConversionInfo> infoMap = new HashMap<String, AttributeConversionInfo>();

		final XClass embeddableXClass = embeddedXProperty.getType();

		// as a baseline, we want to apply conversions from the Embeddable and then overlay conversions
		// from the Embedded

		// first apply conversions from the Embeddable...
		processAttributeConversions( embeddableXClass, infoMap );

		// then we can overlay any conversions from the Embedded attribute
		{
			// @Convert annotation on the Embedded attribute
			final Convert convertAnnotation = embeddedXProperty.getAnnotation( Convert.class );
			if ( convertAnnotation != null ) {
				final AttributeConversionInfo info = new AttributeConversionInfo( convertAnnotation, embeddableXClass );
				if ( StringHelper.isEmpty( info.getAttributeName() ) ) {
					throw new IllegalStateException( "Convert placed on Embedded attribute must define (sub)attributeName" );
				}
				infoMap.put( info.getAttributeName(), info );
			}
		}
		{
			// @Converts annotation on the Embedded attribute
			final Converts convertsAnnotation = embeddedXProperty.getAnnotation( Converts.class );
			if ( convertsAnnotation != null ) {
				for ( Convert convertAnnotation : convertsAnnotation.value() ) {
					final AttributeConversionInfo info = new AttributeConversionInfo( convertAnnotation, embeddableXClass );
					if ( StringHelper.isEmpty( info.getAttributeName() ) ) {
						throw new IllegalStateException( "Convert placed on Embedded attribute must define (sub)attributeName" );
					}
					infoMap.put( info.getAttributeName(), info );
				}
			}
		}

		return infoMap;
	}
