	@Override
	protected AttributeConversionInfo locateAttributeConversionInfo(XProperty property) {
		if ( canElementBeConverted && canKeyBeConverted ) {
			// need to decide whether 'property' refers to key/element
			// todo : this may not work for 'basic collections' since there is no XProperty for the element
		}
		else if ( canKeyBeConverted ) {

		}
		else {
			return null;
		}

		return null;
	}
