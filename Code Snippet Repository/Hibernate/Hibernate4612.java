	private JtaTransactionAdapter makeUserTransactionAdapter() {
		try {
			final UserTransaction userTransaction = jtaPlatform.retrieveUserTransaction();
			if ( userTransaction == null ) {
				log.debug( "JtaPlatform#retrieveUserTransaction returned null" );
			}
			else {
				return new JtaTransactionAdapterUserTransactionImpl( userTransaction );
			}
		}
		catch (Exception ignore) {
			log.debugf( "JtaPlatform#retrieveUserTransaction threw an exception [%s]", ignore.getMessage() );
		}

		return null;
	}
