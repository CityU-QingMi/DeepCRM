	@SuppressWarnings( {""})
	@Test
	public void testDefaultModifiableWithReadOnlyQueryForCollectionEntities() {
		Container cOrig = createContainer();
		Set expectedInitializedObjects = new HashSet(
				Arrays.asList(
						cOrig,
						cOrig.getNoProxyInfo(),
						cOrig.getProxyInfo(),
						cOrig.getNonLazyInfo(),
						cOrig.getNoProxyOwner(),
						cOrig.getProxyOwner(),
						cOrig.getNonLazyOwner(),
						cOrig.getLazyDataPoints().iterator().next(),
						cOrig.getNonLazyJoinDataPoints().iterator().next(),
						cOrig.getNonLazySelectDataPoints().iterator().next()
				)
		);
		Set expectedReadOnlyObjects = new HashSet();

		Session s = openSession();
		assertFalse( s.isDefaultReadOnly() );
		Transaction t = s.beginTransaction();
		s.save( cOrig );
		checkContainer( cOrig, expectedInitializedObjects, expectedReadOnlyObjects, s );
		s.setDefaultReadOnly( true );
		assertTrue( s.isDefaultReadOnly() );
		checkContainer( cOrig, expectedInitializedObjects, expectedReadOnlyObjects, s );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		assertFalse( s.isDefaultReadOnly() );
		DataPoint dp = ( DataPoint ) s.createQuery( "select c.lazyDataPoints from Container c join c.lazyDataPoints where c.id=" + cOrig.getId() )
				.setReadOnly( true ).uniqueResult();
		assertTrue( s.isReadOnly( dp ) );
		t.commit();
		s.close();
		s = openSession();
		t = s.beginTransaction();
		s.createQuery("delete from DataPoint").executeUpdate();
		s.createQuery("delete from Container").executeUpdate();
		s.createQuery("delete from Info").executeUpdate();
		s.createQuery("delete from Owner").executeUpdate();
		t.commit();
		s.close();
	}
