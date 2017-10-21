	public static void commitIfNecessary(Session session) throws JMSException {
		Assert.notNull(session, "Session must not be null");
		try {
			session.commit();
		}
		catch (javax.jms.TransactionInProgressException ex) {
			// Ignore -> can only happen in case of a JTA transaction.
		}
		catch (javax.jms.IllegalStateException ex) {
			// Ignore -> can only happen in case of a JTA transaction.
		}
	}
