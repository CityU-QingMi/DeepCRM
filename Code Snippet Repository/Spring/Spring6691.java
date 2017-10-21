	@Nullable
	public static TopicSession getTransactionalTopicSession(final TopicConnectionFactory cf,
			@Nullable final TopicConnection existingCon, final boolean synchedLocalTransactionAllowed)
			throws JMSException {

		return (TopicSession) doGetTransactionalSession(cf, new ResourceFactory() {
			@Override
			@Nullable
			public Session getSession(JmsResourceHolder holder) {
				return holder.getSession(TopicSession.class, existingCon);
			}
			@Override
			@Nullable
			public Connection getConnection(JmsResourceHolder holder) {
				return (existingCon != null ? existingCon : holder.getConnection(TopicConnection.class));
			}
			@Override
			public Connection createConnection() throws JMSException {
				return cf.createTopicConnection();
			}
			@Override
			public Session createSession(Connection con) throws JMSException {
				return ((TopicConnection) con).createTopicSession(
						synchedLocalTransactionAllowed, Session.AUTO_ACKNOWLEDGE);
			}
			@Override
			public boolean isSynchedLocalTransactionAllowed() {
				return synchedLocalTransactionAllowed;
			}
		}, true);
	}
