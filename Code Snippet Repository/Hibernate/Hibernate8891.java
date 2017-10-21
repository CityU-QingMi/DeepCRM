	@Test
	public void testReattachementUnmodifiedInstance() {
		Session session = openSession();
		session.beginTransaction();
		A a = new A();
		B b = new B();
		b.naturalid = 100;
		session.persist( a );
		session.persist( b ); 
		b.assA = a;
		a.assB.add( b );
		session.getTransaction().commit();
		session.close();


		session = openSession();
		session.beginTransaction();
		session.buildLockRequest(LockOptions.NONE).lock( b ); // HHH-7513 failure during reattachment
		session.delete( b.assA );
		session.delete( b );
		session.flush();

		// true if the re-attachment worked
		assertEquals( session.createQuery( "FROM A" ).list().size(), 0 );
		assertEquals( session.createQuery( "FROM B" ).list().size(), 0 );

		session.getTransaction().commit();
		session.close();
	}
