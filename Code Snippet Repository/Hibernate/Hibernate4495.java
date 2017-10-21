	@Override
	public int executeUpdate() throws HibernateException {
		if ( ! getProducer().isTransactionInProgress() ) {
			throw getProducer().getExceptionConverter().convert(
					new TransactionRequiredException(
							"Executing an update/delete query"
					)
			);
		}
		beforeQuery();
		try {
			return doExecuteUpdate();
		}
		catch ( QueryExecutionRequestException e) {
			throw new IllegalStateException( e );
		}
		catch( TypeMismatchException e ) {
			throw new IllegalArgumentException( e );
		}
		catch ( HibernateException e) {
			if ( getProducer().getFactory().getSessionFactoryOptions().isJpaBootstrap() ) {
				throw getExceptionConverter().convert( e );
			}
			else {
				throw e;
			}
		}
		finally {
			afterQuery();
		}
	}
