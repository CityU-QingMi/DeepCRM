	public AttributeConverterDescriptor resolveElementAttributeConverterDescriptor(XProperty collectionXProperty, XClass elementXClass) {
		AttributeConversionInfo info = locateAttributeConversionInfo( "element" );
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
				"Attempting to locate auto-apply AttributeConverter for collection element [%s]",
				collection.getRole()
		);

		// todo : do we need to pass along `XClass elementXClass`?

		return getContext().getMetadataCollector()
				.getAttributeConverterAutoApplyHandler()
				.findAutoApplyConverterForCollectionElement( collectionXProperty, getContext() );
	}
