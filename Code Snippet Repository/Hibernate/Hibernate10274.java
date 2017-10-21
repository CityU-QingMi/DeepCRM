	@Override
	public Sex convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}

		if ( "M".equals( dbData ) ) {
			return Sex.MALE;
		}
		else if ( "F".equals( dbData ) ) {
			return Sex.FEMALE;
		}

		throw new IllegalArgumentException( "Unexpected Sex db value [" + dbData + "]" );
	}
