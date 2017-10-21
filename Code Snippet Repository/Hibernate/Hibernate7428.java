	@Test
	public void testLobCreation() throws SQLException {
		Session session = sessionFactory().getCurrentSession();
		session.beginTransaction();
		Blob blob = Hibernate.getLobCreator( session ).createBlob( new byte[] {} );
		blob.free();
		Clob clob = Hibernate.getLobCreator( session ).createClob( "Steve" );
		clob.free();
		session.getTransaction().commit();
		assertFalse( session.isOpen() );
	}
