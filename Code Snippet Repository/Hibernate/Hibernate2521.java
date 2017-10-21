	@Override
	public JDBCException convert(SQLException sqlException, String message, String sql) {
		if ( SQLClientInfoException.class.isInstance( sqlException )
				|| SQLInvalidAuthorizationSpecException.class.isInstance( sqlException )
				|| SQLNonTransientConnectionException.class.isInstance( sqlException )
				|| SQLTransientConnectionException.class.isInstance( sqlException ) ) {
			return new JDBCConnectionException( message, sqlException, sql );
		}
		else if ( DataTruncation.class.isInstance( sqlException ) ||
				SQLDataException.class.isInstance( sqlException ) ) {
			throw new DataException( message, sqlException, sql );
		}
		else if ( SQLIntegrityConstraintViolationException.class.isInstance( sqlException ) ) {
			return new ConstraintViolationException(
					message,
					sqlException,
					sql,
					getConversionContext().getViolatedConstraintNameExtracter().extractConstraintName( sqlException )
			);
		}
		else if ( SQLSyntaxErrorException.class.isInstance( sqlException ) ) {
			return new SQLGrammarException( message, sqlException, sql );
		}
		else if ( SQLTimeoutException.class.isInstance( sqlException ) ) {
			return new QueryTimeoutException( message, sqlException, sql );
		}
		else if ( SQLTransactionRollbackException.class.isInstance( sqlException ) ) {
			// Not 100% sure this is completely accurate.  The JavaDocs for SQLTransactionRollbackException state that
			// it indicates sql states starting with '40' and that those usually indicate that:
			//		<quote>
			//			the current statement was automatically rolled back by the database because of deadlock or
			// 			other transaction serialization failures.
			//		</quote>
			return new LockAcquisitionException( message, sqlException, sql );
		}

		return null; // allow other delegates the chance to look
	}
