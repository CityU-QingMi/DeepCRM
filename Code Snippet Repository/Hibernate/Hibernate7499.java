	@Override
	public String convertToDatabaseColumn(PostalArea attribute) {
		toDatabaseCallCount++;
		if ( attribute == null ) {
			return null;
		}
		else {
			return attribute.getZipCode();
		}
	}
