	public static Element addModifiedFlagProperty(Element parent, String propertyName, String suffix, String modifiedFlagName) {
		return addProperty(
				parent,
				(modifiedFlagName != null) ? modifiedFlagName : getModifiedFlagPropertyName( propertyName, suffix ),
				"boolean",
				true,
				false,
				false
		);
	}
