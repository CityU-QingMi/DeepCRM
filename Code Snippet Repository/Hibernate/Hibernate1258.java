	private void buildAttributeConversionInfoMaps(
			XProperty collectionProperty,
			Map<String,AttributeConversionInfo> elementAttributeConversionInfoMap,
			Map<String,AttributeConversionInfo> keyAttributeConversionInfoMap) {
		if ( collectionProperty == null ) {
			// not sure this is valid condition
			return;
		}

		{
			final Convert convertAnnotation = collectionProperty.getAnnotation( Convert.class );
			if ( convertAnnotation != null ) {
				applyLocalConvert( convertAnnotation, collectionProperty, elementAttributeConversionInfoMap, keyAttributeConversionInfoMap );
			}
		}

		{
			final Converts convertsAnnotation = collectionProperty.getAnnotation( Converts.class );
			if ( convertsAnnotation != null ) {
				for ( Convert convertAnnotation : convertsAnnotation.value() ) {
					applyLocalConvert(
							convertAnnotation,
							collectionProperty,
							elementAttributeConversionInfoMap,
							keyAttributeConversionInfoMap
					);
				}
			}
		}
	}
