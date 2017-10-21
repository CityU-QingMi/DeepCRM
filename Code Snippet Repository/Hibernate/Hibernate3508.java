	private boolean isTypeOf(final Statement statement, final Class<? extends Statement> type) {
		if ( isJdbc4 ) {
			try {
				// This is "more correct" than #isInstance, but not always supported.
				return statement.isWrapperFor( type );
			}
			catch (SQLException e) {
				// No operation
			}
			catch (Throwable e) {
				// No operation. Note that this catches more than just SQLException to
				// cover edge cases where a driver might throw an UnsupportedOperationException, AbstractMethodError,
				// etc.  If so, skip permanently.
				isJdbc4 = false;
			}
		}
		return type.isInstance( statement );
	}
