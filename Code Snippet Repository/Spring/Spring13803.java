	@Override
	protected void closeConnection() throws Exception {
		try {
			Session session = this.session;
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		finally {
			this.session = null;
		}
	}
