	@Test
	public void testAutoDiscoveryWithDuplicateColumnLabels() {
		Session session = openSession();
		session.beginTransaction();
		session.save( new User( "steve" ) );
		session.save( new User( "stliu" ) );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		try {
			session.beginTransaction();
			List results = session.createSQLQuery(
					"select u.name, u2.name from t_user u, t_user u2 where u.name='steve'" ).list();
			// this should result in a result set like:
			//   [0] steve, steve
			//   [1] steve, stliu
			// although the rows could be reversed
			assertEquals( 2, results.size() );
			final Object[] row1 = (Object[]) results.get( 0 );
			final Object[] row2 = (Object[]) results.get( 1 );
			assertEquals( "steve", row1[0] );
			assertEquals( "steve", row2[0] );
			if ( "steve".equals( row1[1] ) ) {
				assertEquals( "stliu", row2[1] );
			}
			else {
				assertEquals( "stliu", row1[1] );
			}
			session.getTransaction().commit();
		}
		catch (PersistenceException e) {
			//expected
			assertTyping( NonUniqueDiscoveredSqlAliasException.class, e.getCause() );
		}
		session.close();

		session = openSession();
		session.beginTransaction();
		session.createQuery( "delete from User" ).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
