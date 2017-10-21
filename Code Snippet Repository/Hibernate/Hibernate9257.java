	private void cleanup() {
		Session s = sessionFactory().openSession();
		s.beginTransaction();

		s.createQuery( "delete from VersionedNode where parent is not null" ).executeUpdate();
		s.createQuery( "delete from VersionedNode" ).executeUpdate();

		s.getTransaction().commit();
		s.close();
	}
