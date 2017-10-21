	protected Connection createSharedConnection() throws JMSException {
		Connection con = createConnection();
		try {
			prepareSharedConnection(con);
			return con;
		}
		catch (JMSException ex) {
			JmsUtils.closeConnection(con);
			throw ex;
		}
	}
