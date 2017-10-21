	@Test
	public void testSetSimpleValueTypeNameInSecondPass() throws Exception {
		Peugot derived = new Peugot();
		derived.setName("sharath");
		
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		s.persist(derived);
		tx.commit();
		s.close();
		
		s = openSession();
		tx = s.beginTransaction();
		derived = (Peugot) s.get( Peugot.class, derived.getId() );
		assertNotNull( derived );
		assertEquals( "SHARATH", derived.getName() );
		s.delete(derived);
		tx.commit();
		s.close();
	}
