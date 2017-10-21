	@Override
	public String convertToDatabaseColumn(Sex attribute) {
		if (attribute == null) {
			return null;
		}

		switch (attribute) {
			case MALE: {
				return "M";
			}
			case FEMALE: {
				return "F";
			}
			default: {
				throw new IllegalArgumentException( "Unexpected Sex model value [" + attribute + "]" );
			}
		}
	}
