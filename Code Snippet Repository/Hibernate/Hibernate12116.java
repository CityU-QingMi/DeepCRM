	public static boolean isSqlLockTimeout(Exception e) {
		// grr, exception can be any number of types based on database
		// 		see HHH-6887
		if ( LockAcquisitionException.class.isInstance( e )
				|| LockTimeoutException.class.isInstance( e )
				|| GenericJDBCException.class.isInstance( e )
				|| PessimisticLockException.class.isInstance( e )
				|| javax.persistence.PessimisticLockException.class.isInstance( e )
				|| JDBCConnectionException.class.isInstance( e ) ) {
			return true;
		}
		else {
			Throwable rootCause = ExceptionUtil.rootCause( e );
			if ( rootCause != null && (
					rootCause.getMessage().contains( "timeout" ) ||
							rootCause.getMessage().contains( "timed out" ) )
					) {
				return true;
			}
		}
		return false;
	}
