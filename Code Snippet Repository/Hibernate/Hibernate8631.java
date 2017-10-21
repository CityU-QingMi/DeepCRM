	private void createMainObject() throws HibernateException {
		Session session = openSession();
		Transaction tx = session.beginTransaction();

		MainObject mo = new MainObject();
		mo.setDescription( "Main Test" );

		generatedId = session.save( mo );

		tx.commit();
		session.close();
	}
