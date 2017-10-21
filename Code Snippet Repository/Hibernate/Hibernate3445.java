	private static Method getMethod(Class<?> clazz, String attributeName) {
		try {
			char[] string = attributeName.toCharArray();
			string[0] = Character.toUpperCase( string[0] );
			String casedAttributeName = new String( string );
			try {
				return clazz.getDeclaredMethod( "get" + casedAttributeName );
			}
			catch ( NoSuchMethodException e ) {
				return clazz.getDeclaredMethod( "is" + casedAttributeName );
			}
		}
		catch ( NoSuchMethodException e ) {
			return null;
		}
	}
