	@Override
	public List list() throws HibernateException {
		before();
		try {
			return session.list( this );
		}
		finally {
			after();
		}
	}
