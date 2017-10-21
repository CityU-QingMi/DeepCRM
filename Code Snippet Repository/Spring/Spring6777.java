	protected void rollbackOnExceptionIfNecessary(Session session, Throwable ex) throws JMSException {
		try {
			if (session.getTransacted()) {
				if (isSessionLocallyTransacted(session)) {
					// Transacted session created by this container -> rollback.
					if (logger.isDebugEnabled()) {
						logger.debug("Initiating transaction rollback on application exception", ex);
					}
					JmsUtils.rollbackIfNecessary(session);
				}
			}
			else if (isClientAcknowledge(session)) {
				session.recover();
			}
		}
		catch (IllegalStateException ex2) {
			logger.debug("Could not roll back because Session already closed", ex2);
		}
		catch (JMSException | RuntimeException | Error ex2) {
			logger.error("Application exception overridden by rollback error", ex);
			throw ex2;
		}
	}
