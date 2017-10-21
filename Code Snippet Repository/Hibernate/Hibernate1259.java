	private String removePrefix(String path, String prefix, String defaultValue) {
		if ( path.equals(prefix) ) {
			return "";
		}

		if (path.startsWith(prefix + ".")) {
			return path.substring( prefix.length() + 1 );
		}

		return defaultValue;
	}
