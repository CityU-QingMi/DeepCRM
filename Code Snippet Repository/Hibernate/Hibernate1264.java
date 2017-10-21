	public AttributeConverterDescriptor mapKeyAttributeConverterDescriptor(XProperty mapXProperty, XClass keyXClass) {
		AttributeConversionInfo info = locateAttributeConversionInfo( "key" );
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

		log.debugf(
				"Attempting to locate auto-apply AttributeConverter for collection key [%s]",
				collection.getRole()
		);

		// todo : do we need to pass along `XClass keyXClass`?

		return getContext().getMetadataCollector()
				.getAttributeConverterAutoApplyHandler()
				.findAutoApplyConverterForMapKey( mapXProperty, getContext() );
	}
