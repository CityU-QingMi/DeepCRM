	public DataPoint load(long id) {
		Session s = hibernateUtil.getSession();
		s.getTransaction().begin();
		DataPoint dp = (DataPoint) s.load( DataPoint.class, new Long(id) );
		// initialize
		dp.getName();
		s.getTransaction().commit();
		s.close();
		return dp;
	}
