	@Nullable
	private <T> T executeLocal(SessionCallback<T> action, boolean startConnection) throws JmsException {
		Assert.notNull(action, "Callback object must not be null");
		Connection con = null;
		Session session = null;
		try {
			con = createConnection();
			session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			if (startConnection) {
				con.start();
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Executing callback on JMS Session: " + session);
			}
			return action.doInJms(session);
		}
		catch (JMSException ex) {
			throw convertJmsAccessException(ex);
		}
		finally {
			JmsUtils.closeSession(session);
			ConnectionFactoryUtils.releaseConnection(con, getConnectionFactory(), startConnection);
		}
	}
