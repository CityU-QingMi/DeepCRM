	public List listEvents() {

		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();

		List result = session.createQuery("from Event").setCacheable(true).list();

		session.getTransaction().commit();

		return result;
	}
