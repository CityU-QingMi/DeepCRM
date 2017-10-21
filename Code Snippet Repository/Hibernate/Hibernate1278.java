	private String extractUserPropertyName(String redundantString, String propertyName) {
		String result = null;
		String className = component.getOwner().getClassName();
		if ( propertyName.startsWith( className )
				&& propertyName.length() > className.length() + 2 + redundantString.length() // .id.
				&& propertyName.substring(
				className.length() + 1, className.length() + 1 + redundantString.length()
		).equals( redundantString )
				) {
			//remove id we might be in a @IdCLass case
			result = className + propertyName.substring( className.length() + 1 + redundantString.length() );
		}
		return result;
	}
