	@Test
	public void testDynamicBatchFetch() {
		Integer aId1 = createAAndB();
		Integer aId2 = createAAndB();
		Session s = openSession();
		s.getTransaction().begin();
		List resultList = s.createQuery("from A where id in (" + aId1 + "," + aId2 + ") order by id" ).list();
		A a1 = (A) resultList.get(0);
		A a2 = (A) resultList.get( 1 );
		assertEquals( aId1, a1.getId() );
		assertEquals( aId2, a2.getId() );
		assertFalse( Hibernate.isInitialized( a1.getB() ) );
		assertFalse( Hibernate.isInitialized( a2.getB() ) );
		assertEquals( "foo", a1.getB().getOtherProperty() );
		assertTrue( Hibernate.isInitialized( a1.getB() ) );
		// a2.getB() is still uninitialized
		assertFalse( Hibernate.isInitialized( a2.getB() ) );
		// the B entity has been loaded, but is has not been made the target of a2.getB() yet.
		assertTrue( ( (SessionImplementor) session ).getPersistenceContext().containsEntity(
						new EntityKey(
								( (SessionImplementor) session ).getContextEntityIdentifier( a2.getB() ),
								( (SessionImplementor) session ).getFactory().getEntityPersister( B.class.getName() )
						)
				)
		);
		// a2.getB() is still uninitialized; getting the ID for a2.getB() did not initialize it.
		assertFalse( Hibernate.isInitialized( a2.getB() ) );
		assertEquals( "foo", a2.getB().getOtherProperty() );
		// now it's initialized.
		assertTrue( Hibernate.isInitialized( a2.getB() ) );
		s.getTransaction().commit();
		s.close();

	}
