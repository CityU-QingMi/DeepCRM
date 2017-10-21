	@Override
	public void startingProperty(XProperty property) {
		if ( property == null ) {
			return;
		}

		final String propertyName = property.getName();
		if ( attributeConversionInfoMap.containsKey( propertyName ) ) {
			return;
		}

		{
			// @Convert annotation on the Embeddable attribute
			final Convert convertAnnotation = property.getAnnotation( Convert.class );
			if ( convertAnnotation != null ) {
				final AttributeConversionInfo info = new AttributeConversionInfo( convertAnnotation, property );
				if ( StringHelper.isEmpty( info.getAttributeName() ) ) {
					attributeConversionInfoMap.put( propertyName, info );
				}
				else {
					attributeConversionInfoMap.put( propertyName + '.' + info.getAttributeName(), info );
				}
			}
		}
		{
			// @Converts annotation on the Embeddable attribute
			final Converts convertsAnnotation = property.getAnnotation( Converts.class );
			if ( convertsAnnotation != null ) {
				for ( Convert convertAnnotation : convertsAnnotation.value() ) {
					final AttributeConversionInfo info = new AttributeConversionInfo( convertAnnotation, property );
					if ( StringHelper.isEmpty( info.getAttributeName() ) ) {
						attributeConversionInfoMap.put( propertyName, info );
					}
					else {
						attributeConversionInfoMap.put( propertyName + '.' + info.getAttributeName(), info );
					}
				}
			}
		}
	}
