	public static void releaseConnection(@Nullable Connection con, @Nullable ConnectionFactory cf, boolean started) {
		if (con == null) {
			return;
		}
		if (started && cf instanceof SmartConnectionFactory && ((SmartConnectionFactory) cf).shouldStop(con)) {
			try {
				con.stop();
			}
			catch (Throwable ex) {
				logger.debug("Could not stop JMS Connection before closing it", ex);
			}
		}
		try {
			con.close();
		}
		catch (Throwable ex) {
			logger.debug("Could not close JMS Connection", ex);
		}
	}
