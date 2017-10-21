	private void joinTransaction(boolean explicitRequest) {
		if ( !getTransactionCoordinator().getTransactionCoordinatorBuilder().isJta() ) {
			if ( explicitRequest ) {
				log.callingJoinTransactionOnNonJtaEntityManager();
			}
			return;
		}

		try {
			getTransactionCoordinator().explicitJoin();
		}
		catch (TransactionRequiredForJoinException e) {
			throw new TransactionRequiredException( e.getMessage() );
		}
		catch (HibernateException he) {
			throw exceptionConverter.convert( he );
		}
	}
