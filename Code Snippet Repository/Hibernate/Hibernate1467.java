	private String qualifyConverterAttributeName(String attributeNamePrefix, String specifiedAttributeName) {
		String qualifiedAttributeName;
		if ( StringHelper.isNotEmpty( specifiedAttributeName ) ) {
			if ( StringHelper.isNotEmpty( attributeNamePrefix ) ) {
				qualifiedAttributeName = attributeNamePrefix + '.' + specifiedAttributeName;
			}
			else {
				qualifiedAttributeName = specifiedAttributeName;
			}
		}
		else {
			qualifiedAttributeName = "";
		}
		return qualifiedAttributeName;
	}
