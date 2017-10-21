	@Override
	protected void cleanupTest() {
		Session s = null;
		s = openSession();
		s.beginTransaction();
		s.createQuery("delete MultipleCollectionRefEntity1").executeUpdate();
		s.createQuery("delete MultipleCollectionRefEntity2").executeUpdate();
		s.createQuery("delete MultipleCollectionEntity").executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
