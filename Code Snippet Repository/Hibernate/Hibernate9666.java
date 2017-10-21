	@Remove
	public void stop() {

		try {
			utx.begin();
		}
		catch (Exception e) {
			throw new RuntimeException( "Unable to start JTA transaction via UserTransaction", e );
		}

		try {
			// closing the SF should run the delayed schema drop delegate
			emf.close();
		}
		catch (RuntimeException e) {
			try {
				utx.rollback();
			}
			catch (Exception e1) {
				throw new RuntimeException( "Unable to rollback JTA transaction via UserTransaction", e );
			}
		}

		try {
			utx.commit();
		}
		catch (Exception e) {
			throw new RuntimeException( "Unable to commit JTA transaction via UserTransaction", e );
		}
	}
