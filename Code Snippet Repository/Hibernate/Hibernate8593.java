	@Test
	public void testLimitZero() throws Exception {
		TransactionUtil.doInHibernate( this::sessionFactory, s -> {
			Iterator iter = s.createQuery( "from Person p" )
					.setMaxResults( 0 )
					.iterate();
			int count = 0;
			while ( iter.hasNext() ) {
				iter.next();
				count++;
			}
			assertEquals( 0, count );
			final List list = s.createQuery( "select p from Person p" )
					.setMaxResults( 0 )
					.setFirstResult( 2 )
					.list();
			assertTrue( list.isEmpty() );
		} );
	}
