	@Test
	public void testBackRefToProxiedEntityOnMerge() {
		Session s = openSession();
		s.beginTransaction();
		Parent me = new Parent( "Steve", 192837465 );
		me.getChildren().add( new Child( "Joe" ) );
  		s.persist( me );
		s.getTransaction().commit();
		s.close();

		// while detached, add a new element
		me.getChildren().add( new Child( "Cece" ) );
		me.getChildren().add( new Child( "Austin" ) );

		s = openSession();
		s.beginTransaction();
		// load 'me' to associate it with the new session as a proxy (this may have occurred as 'prior work'
		// to the reattachment below)...
		Object meProxy = s.load( Parent.class, me.getName() );
		assertFalse( Hibernate.isInitialized( meProxy ) );
		// now, do the reattchment...
		s.merge( me );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.createQuery( "delete from Child" ).executeUpdate();
		s.createQuery( "delete from Parent" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
