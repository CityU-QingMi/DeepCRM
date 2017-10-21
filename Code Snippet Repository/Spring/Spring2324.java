	@Nullable
	protected String getStringOrNull(ResourceBundle bundle, String key) {
		if (bundle.containsKey(key)) {
			try {
				return bundle.getString(key);
			}
			catch (MissingResourceException ex){
				// Assume key not found for some other reason
				// -> do NOT throw the exception to allow for checking parent message source.
			}
		}
		return null;
	}
