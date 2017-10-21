	protected boolean receiveAndExecute(
			Object invoker, @Nullable Session session, @Nullable MessageConsumer consumer)
			throws JMSException {

		if (this.transactionManager != null) {
			// Execute receive within transaction.
			TransactionStatus status = this.transactionManager.getTransaction(this.transactionDefinition);
			boolean messageReceived;
			try {
				messageReceived = doReceiveAndExecute(invoker, session, consumer, status);
			}
			catch (JMSException | RuntimeException | Error ex) {
				rollbackOnException(this.transactionManager, status, ex);
				throw ex;
			}
			this.transactionManager.commit(status);
			return messageReceived;
		}

		else {
			// Execute receive outside of transaction.
			return doReceiveAndExecute(invoker, session, consumer, null);
		}
	}
