	@Nullable
	public static QueueSession getTransactionalQueueSession(final QueueConnectionFactory cf,
			@Nullable final QueueConnection existingCon, final boolean synchedLocalTransactionAllowed)
			throws JMSException {

		return (QueueSession) doGetTransactionalSession(cf, new ResourceFactory() {
			@Override
			@Nullable
			public Session getSession(JmsResourceHolder holder) {
				return holder.getSession(QueueSession.class, existingCon);
			}
			@Override
			@Nullable
			public Connection getConnection(JmsResourceHolder holder) {
				return (existingCon != null ? existingCon : holder.getConnection(QueueConnection.class));
			}
			@Override
			public Connection createConnection() throws JMSException {
				return cf.createQueueConnection();
			}
			@Override
			public Session createSession(Connection con) throws JMSException {
				return ((QueueConnection) con).createQueueSession(synchedLocalTransactionAllowed, Session.AUTO_ACKNOWLEDGE);
			}
			@Override
			public boolean isSynchedLocalTransactionAllowed() {
				return synchedLocalTransactionAllowed;
			}
		}, true);
	}
