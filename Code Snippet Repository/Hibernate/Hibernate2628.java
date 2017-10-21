	private String formatMissingContructorExceptionMessage(String className) {
		String[] params = new String[constructorArgumentTypes.length];
		for ( int j = 0; j < constructorArgumentTypes.length; j++ ) {
			params[j] = constructorArgumentTypes[j] instanceof PrimitiveType
					? ( (PrimitiveType) constructorArgumentTypes[j] ).getPrimitiveClass().getName()
					: constructorArgumentTypes[j].getReturnedClass().getName();
		}
		String formattedList = params.length == 0 ? "no arguments constructor" : StringHelper.join( ", ", params );
		return String.format(
				"Unable to locate appropriate constructor on class [%s]. Expected arguments are: %s",
				className, formattedList
		);
	}
