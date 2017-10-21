	protected void rollbackIfNecessary(Session session) throws JMSException {
		if (session.getTransacted()) {
			if (isSessionLocallyTransacted(session)) {
				// Transacted session created by this container -> rollback.
				JmsUtils.rollbackIfNecessary(session);
			}
		}
		else if (isClientAcknowledge(session)) {
			session.recover();
		}
	}
