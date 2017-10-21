	private Type getNakedPropertyType(FromElement fromElement) {
		if (fromElement == null) {
			return null;
		}
		String property = getOriginalText();
		Type propertyType = null;
		try {
			propertyType = fromElement.getPropertyType(property, property);
		}
		catch (Throwable ignore) {
		}
		return propertyType;
	}
