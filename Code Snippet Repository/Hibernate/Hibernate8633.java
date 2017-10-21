	private MainObject readMainObject() throws HibernateException {
		Long returnId = null;
		Session session = openSession();
		Transaction tx = session.beginTransaction();

		Serializable id = generatedId;

		MainObject mo = ( MainObject ) session.get( MainObject.class, id );

		tx.commit();
		session.close();

		return mo;
	}
