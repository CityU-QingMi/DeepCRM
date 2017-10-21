	@Nullable
	public static Session getTransactionalSession(final ConnectionFactory cf,
			@Nullable final Connection existingCon, final boolean synchedLocalTransactionAllowed)
			throws JMSException {

		return doGetTransactionalSession(cf, new ResourceFactory() {
			@Override
			@Nullable
			public Session getSession(JmsResourceHolder holder) {
				return holder.getSession(Session.class, existingCon);
			}
			@Override
			@Nullable
			public Connection getConnection(JmsResourceHolder holder) {
				return (existingCon != null ? existingCon : holder.getConnection());
			}
			@Override
			public Connection createConnection() throws JMSException {
				return cf.createConnection();
			}
			@Override
			public Session createSession(Connection con) throws JMSException {
				return con.createSession(synchedLocalTransactionAllowed, Session.AUTO_ACKNOWLEDGE);
			}
			@Override
			public boolean isSynchedLocalTransactionAllowed() {
				return synchedLocalTransactionAllowed;
			}
		}, true);
	}
