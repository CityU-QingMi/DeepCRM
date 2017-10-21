	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if ( "findColumn".equals( method.getName() ) ) {
			return findColumn( (String) args[0] );
		}

		if ( isFirstArgColumnLabel( method, args ) ) {
			try {
				final Integer columnIndex = findColumn( (String) args[0] );
				return invokeMethod(
						locateCorrespondingColumnIndexMethod( method ),
						buildColumnIndexMethodArgs( args, columnIndex )
				);
			}
			catch ( SQLException ex ) {
				final String msg = "Exception getting column index for column: [" + args[0] +
						"].\nReverting to using: [" + args[0] +
						"] as first argument for method: [" + method + "]";
				SQL_EXCEPTION_HELPER.logExceptions( ex, msg );
			}
			catch ( NoSuchMethodException ex ) {
				LOG.unableToSwitchToMethodUsingColumnIndex( method );
			}
		}
		return invokeMethod( method, args );
	}
