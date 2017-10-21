	@Override
	public AttributeConverterDescriptor resolveAttributeConverterDescriptor(XProperty property) {
		AttributeConversionInfo info = locateAttributeConversionInfo( property );
		if ( info != null ) {
			if ( info.isConversionDisabled() ) {
				return null;
			}
			else {
				try {
					return makeAttributeConverterDescriptor( info );
				}
				catch (Exception e) {
					throw buildExceptionFromInstantiationError( info, e );
				}
			}
		}

		log.debugf( "Attempting to locate auto-apply AttributeConverter for property [%s:%s]", path, property.getName() );

		return context.getMetadataCollector()
				.getAttributeConverterAutoApplyHandler()
				.findAutoApplyConverterForAttribute( property, context );
	}
