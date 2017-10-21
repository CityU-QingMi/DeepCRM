	@Test
	public void testInsertWithAssignedCompositeId() {
		// this just checks that the query parser detects that we are explicitly inserting a composite id
		Session s = openSession();
		s.beginTransaction();
		// intentionally reversing the order of the composite id properties to make sure that is supported too
		s.createQuery( "insert into CompositeIdEntity (key2, someProperty, key1) select a.key2, 'COPY', a.key1 from CompositeIdEntity a" ).executeUpdate();
		s.createQuery( "delete from CompositeIdEntity" ).executeUpdate();
		s.getTransaction().commit();
		s.close();		
	}
