	@Override
	protected AttributeConversionInfo locateAttributeConversionInfo(XProperty property) {
		final String propertyName = property.getName();

		// conversions on parent would have precedence
		AttributeConversionInfo conversion = locateAttributeConversionInfo( propertyName );
		if ( conversion != null ) {
			return conversion;
		}

		return null;
	}
