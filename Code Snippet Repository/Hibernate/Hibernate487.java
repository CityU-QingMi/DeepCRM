	public static CacheMode interpretExternalSetting(String setting) {
		if (setting == null) {
			return null;
		}

		try {
			return CacheMode.valueOf( setting.toUpperCase(Locale.ROOT) );
		}
		catch ( IllegalArgumentException e ) {
			throw new MappingException( "Unknown Cache Mode: " + setting );
		}
	}
