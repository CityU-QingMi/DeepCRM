	@Test
	@TestForIssue( jiraKey = "" )
	public void testImmutableNaturalIdLifecycle2() {
		Building b1 = new Building();
		b1.setName( "Computer Science" );
		b1.setAddress( "1210 W. Dayton St." );
		b1.setCity( "Madison" );
		b1.setState( "WI" );

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.persist( b1 );
		tx.commit();
		s.close();


		s = openSession();
		tx = s.beginTransaction();
		NaturalIdLoadAccess naturalIdLoader = s.byNaturalId( Building.class );
		naturalIdLoader.using( "address", "1210 W. Dayton St." ).using( "city", "Madison" ).using( "state", "WI" );
		Building building = (Building) naturalIdLoader.getReference();
		assertNotNull( building );

		s.delete( building );
		building = (Building) naturalIdLoader.load();
		//org.hibernate.ObjectNotFoundException: No row with the given identifier exists: [org.hibernate.test.naturalid.immutableentity.Building#1]
//		at org.hibernate.internal.SessionFactoryImpl$1$1.handleEntityNotFound(SessionFactoryImpl.java:247)
//		at org.hibernate.event.internal.DefaultLoadEventListener.returnNarrowedProxy(DefaultLoadEventListener.java:282)
//		at org.hibernate.event.internal.DefaultLoadEventListener.proxyOrLoad(DefaultLoadEventListener.java:248)
//		at org.hibernate.event.internal.DefaultLoadEventListener.onLoad(DefaultLoadEventListener.java:148)
//		at org.hibernate.internal.SessionImpl.fireLoad(SessionImpl.java:1079)
//		at org.hibernate.internal.SessionImpl.access$13(SessionImpl.java:1075)
//		at org.hibernate.internal.SessionImpl$IdentifierLoadAccessImpl.load(SessionImpl.java:2425)
//		at org.hibernate.internal.SessionImpl$NaturalIdLoadAccessImpl.load(SessionImpl.java:2586)
//		at org.hibernate.test.naturalid.immutableentity.ImmutableEntityNaturalIdTest.testImmutableNaturalIdLifecycle2(ImmutableEntityNaturalIdTest.java:188)

		assertNull( building );

		tx.commit();
		s.close();
	}
