	private String getProperty(String key) {
		Preconditions.notBlank(key, "key must not be null or blank");

		// 1) Check explicit config param.
		String value = this.explicitConfigParams.get(key);

		// 2) Check system property.
		if (value == null) {
			try {
				value = System.getProperty(key);
			}
			catch (Exception ex) {
				/* ignore */
			}

			// 3) Check config file.
			if (value == null) {
				value = this.configParamsFromFile.getProperty(key);
			}
		}

		return value;
	}
