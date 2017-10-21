	@Test
	public void testNaturalIdQuerySupportingNullValues() {
		Session session = openSession();
		session.beginTransaction();
		D d1 = new D();
		d1.name = "Titi";
		d1.associatedC = null;
		D d2 = new D();
		d2.name = null;
		C c = new C();
		d2.associatedC = c;
		session.persist( d1 );
		session.persist( d2 );
		session.persist( c );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		assertNotNull( session.byNaturalId( D.class ).using( "name", null ).using( "associatedC", c ).load() );
		assertNotNull( session.byNaturalId( D.class ).using( "name", "Titi" ).using( "associatedC", null ).load() );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		session.delete( c );
		session.delete( d1 );
		session.delete( d2 );
		session.getTransaction().commit();
		session.close();
	}
