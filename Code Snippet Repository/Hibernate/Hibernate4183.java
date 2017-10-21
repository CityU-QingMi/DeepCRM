	public String getSubclassForDiscriminatorValue(Object value) {
		if ( value == null ) {
			return subclassesByDiscriminatorValue.get( NULL_DISCRIMINATOR );
		}
		else {
			String result = subclassesByDiscriminatorValue.get( value );
			if ( result == null ) {
				result = subclassesByDiscriminatorValue.get( NOT_NULL_DISCRIMINATOR );
			}
			return result;
		}
	}
