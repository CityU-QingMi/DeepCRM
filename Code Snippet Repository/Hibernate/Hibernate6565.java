	private Serializable save(Object o) {
		Session session = openNewSession();
		session.getTransaction().begin();

		Serializable id = session.save( o );

		session.getTransaction().commit();
		session.close();

		return id;
	}
