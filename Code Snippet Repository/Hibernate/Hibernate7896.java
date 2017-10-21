	@Test
	public void testCrazyIdFieldNames() {
		MoreCrazyIdFieldNameStuffEntity top = new MoreCrazyIdFieldNameStuffEntity( "top" );
		HeresAnotherCrazyIdFieldName next = new HeresAnotherCrazyIdFieldName( "next" );
		top.setHeresAnotherCrazyIdFieldName( next );
		MoreCrazyIdFieldNameStuffEntity other = new MoreCrazyIdFieldNameStuffEntity( "other" );
		Session s = openSession();
		s.beginTransaction();
		s.save( next );
		s.save( top );
		s.save( other );
		s.flush();

		List results = s.createQuery( "select e.heresAnotherCrazyIdFieldName from MoreCrazyIdFieldNameStuffEntity e where e.heresAnotherCrazyIdFieldName is not null" ).list();
		assertEquals( 1, results.size() );
		Object result = results.get( 0 );
		assertClassAssignability( HeresAnotherCrazyIdFieldName.class, result.getClass() );
		assertSame( next, result );

		results = s.createQuery( "select e.heresAnotherCrazyIdFieldName.heresAnotherCrazyIdFieldName from MoreCrazyIdFieldNameStuffEntity e where e.heresAnotherCrazyIdFieldName is not null" ).list();
		assertEquals( 1, results.size() );
		result = results.get( 0 );
		assertClassAssignability( Long.class, result.getClass() );
		assertEquals( next.getHeresAnotherCrazyIdFieldName(), result );

		results = s.createQuery( "select e.heresAnotherCrazyIdFieldName from MoreCrazyIdFieldNameStuffEntity e" ).list();
		assertEquals( 1, results.size() );
		Iterator itr = s.createQuery( "select e.heresAnotherCrazyIdFieldName from MoreCrazyIdFieldNameStuffEntity e" ).iterate();
		assertTrue( itr.hasNext() ); itr.next(); assertFalse( itr.hasNext() );

		s.delete( top );
		s.delete( next );
		s.delete( other );
		s.getTransaction().commit();
		s.close();
	}
