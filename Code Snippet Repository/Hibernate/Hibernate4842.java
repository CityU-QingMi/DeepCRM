	public static boolean interpretNamespaceHandling(Map configurationValues) {
		// prefer the JPA setting...
		return ConfigurationHelper.getBoolean(
				AvailableSettings.HBM2DLL_CREATE_SCHEMAS,
				configurationValues,
				ConfigurationHelper.getBoolean(
						AvailableSettings.HBM2DLL_CREATE_NAMESPACES,
						configurationValues,
						false
				)
		);
	}
