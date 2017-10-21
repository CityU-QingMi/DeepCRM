	public static boolean supportsRefCursors(DatabaseMetaData meta) {
		// Standard JDBC REF_CURSOR support was not added until Java 8, so we need to use reflection to attempt to
		// access these fields/methods...
		try {
			return (Boolean) meta.getClass().getMethod( "supportsRefCursors" ).invoke( meta );
		}
		catch (NoSuchMethodException e) {
			log.trace( "JDBC DatabaseMetaData class does not define supportsRefCursors method..." );
		}
		catch (Exception e) {
			log.debug( "Unexpected error trying to gauge level of JDBC REF_CURSOR support : " + e.getMessage() );
		}
		return false;
	}
