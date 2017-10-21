	private void printConfig() {
		String sql = "select value from V$NLS_PARAMETERS where parameter = 'NLS_CHARACTERSET'";
		
		Session session = openSession();
		session.beginTransaction();
		Query query = session.createSQLQuery( sql );
		
		String s = (String) query.uniqueResult();
		LOG.debug( "Using Oracle charset " + s );
	}
