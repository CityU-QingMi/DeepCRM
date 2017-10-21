	@Override
	public void startingProperty(XProperty property) {
		if ( property == null ) {
			return;
		}

//		if ( virtual ) {
//			return;
//		}

		// again : the property coming in here *should* be the property on the embeddable (Address#city in the example),
		// so we just ignore it if there is already an existing conversion info for that path since they would have
		// precedence

		// technically we should only do this for properties of "basic type"

		final String path = embeddedAttributeName + '.' + property.getName();
		if ( attributeConversionInfoMap.containsKey( path ) ) {
			return;
		}

		{
			// @Convert annotation on the Embeddable attribute
			final Convert convertAnnotation = property.getAnnotation( Convert.class );
			if ( convertAnnotation != null ) {
				final AttributeConversionInfo info = new AttributeConversionInfo( convertAnnotation, property );
				attributeConversionInfoMap.put( property.getName(), info );
			}
		}
		{
			// @Converts annotation on the Embeddable attribute
			final Converts convertsAnnotation = property.getAnnotation( Converts.class );
			if ( convertsAnnotation != null ) {
				for ( Convert convertAnnotation : convertsAnnotation.value() ) {
					final AttributeConversionInfo info = new AttributeConversionInfo( convertAnnotation, property );
					attributeConversionInfoMap.put( property.getName(), info );
				}
			}
		}
	}
