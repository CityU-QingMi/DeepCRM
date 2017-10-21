	private void delete(Serializable id) {
		Session session = openNewSession();
		session.getTransaction().begin();

		Object o = session.get( EntityMapEnum.class, id );
		session.delete( o );

		session.getTransaction().commit();
		session.close();
	}
