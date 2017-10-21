	public static boolean isConnectionClose(Exception e) {
		Throwable rootCause = ExceptionUtil.rootCause( e );
		if ( rootCause != null && (
				rootCause.getMessage().toLowerCase().contains( "connection is close" ) ||
				rootCause.getMessage().toLowerCase().contains( "closed connection" )
		) ) {
			return true;
		}
		return false;
	}
