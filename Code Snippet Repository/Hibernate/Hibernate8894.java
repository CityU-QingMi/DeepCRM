	@Test
	public void testUniqueAssociation() {
		Session session = openSession();
		session.beginTransaction();
		A a = new A();
		B b = new B();
		b.naturalid = 100;
		session.persist( a );
		session.persist( b ); //b.assA is declared NaturalId, his value is null this moment
		b.assA = a;
		a.assB.add( b );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		// this is OK
		assertNotNull( session.byNaturalId( B.class ).using( "naturalid", 100 ).using( "assA", a ).load() );
		// this fails, cause EntityType.compare(Object x, Object y) always returns 0 !
		assertNull( session.byNaturalId( B.class ).using( "naturalid", 100 ).using( "assA", null ).load() );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		session.delete( b );
		session.delete( a );
		session.getTransaction().commit();
		session.close();
	}
