	@Test
	@TestForIssue( jiraKey = "" )
	public void testGetByCompositeId() {
		Session s = openSession();
		s.beginTransaction();
		s.persist( new EntityWithCompositeKey( PK ) );
		Query query = s.createQuery( "FROM EntityWithCompositeKey e WHERE e.pk = :pk" );
		query.setCacheable( true );
		query.setParameter( "pk", PK );
		assertEquals(1, query.list().size( ));
		s.getTransaction().rollback();
		s.close();
		
		s = openSession();
		s.beginTransaction();
		EntityWithStringCompositeKey entity = new EntityWithStringCompositeKey();
		StringCompositeKey key = new StringCompositeKey();
		key.setAnalog( "foo1" );
		key.setDevice( "foo2" );
		key.setDeviceType( "foo3" );
		key.setSubstation( "foo4" );
		entity.setPk( key );
		s.persist( entity );
		Criteria c = s.createCriteria(
				EntityWithStringCompositeKey.class ).add( Restrictions.eq( 
						"pk", key ) );
		c.setCacheable( true );
		assertEquals( 1, c.list().size() );
		s.getTransaction().rollback();
		s.close();
	}
