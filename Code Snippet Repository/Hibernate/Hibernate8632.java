	private void addObject2() throws HibernateException {
		Session session = openSession();
		Transaction tx = session.beginTransaction();

		MainObject mo =
				( MainObject ) session.load( MainObject.class, generatedId );

		Object2 toAdd = new Object2();
		toAdd.setDummy( "test" );

		//toAdd should now be saved by cascade
		mo.setObj2( toAdd );

		tx.commit();
		session.close();
	}
