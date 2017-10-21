	private GrantedPermission parseJaccConfigEntry(String keyString, String valueString) {
		try {
			final int roleStart = JACC_PREFIX.length() + 1;
			final String role = keyString.substring( roleStart, keyString.indexOf( '.', roleStart ) );
			final int classStart = roleStart + role.length() + 1;
			final String clazz = keyString.substring( classStart, keyString.length() );
			return new GrantedPermission( role, clazz, valueString );
		}
		catch ( IndexOutOfBoundsException e ) {
			throw persistenceException( "Illegal usage of " + JACC_PREFIX + ": " + keyString );
		}
	}
