	@Test
	public void testUsage() {
		Session session = openSession();
		session.beginTransaction();
		Node node = new Node();
		session.save( node );
		assertNotNull( node.getId() );
		assertEquals( 2, node.getId().variant() );
		assertEquals( 1, node.getId().version() );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		session.delete( node );
		session.getTransaction().commit();
		session.close();
	}
