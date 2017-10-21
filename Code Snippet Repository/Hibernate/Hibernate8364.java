	@Test
	public void testBasicRelease() {
//		Session session = openSession();
//		SessionImplementor sessionImpl = (SessionImplementor) session;
//
//		LogicalConnectionImplementor logicalConnection = new LogicalConnectionImpl( null,
//				ConnectionReleaseMode.AFTER_STATEMENT, services, new JdbcConnectionAccessImpl(
//						services.getConnectionProvider() ) );
//
//		JdbcCoordinatorImpl jdbcCoord = new JdbcCoordinatorImpl( logicalConnection,
//				sessionImpl );
//		JournalingConnectionObserver observer = new JournalingConnectionObserver();
//		logicalConnection.addObserver( observer );
//
//		try {
//			PreparedStatement ps = jdbcCoord.getStatementPreparer().prepareStatement( "insert into SANDBOX_JDBC_TST( ID, NAME ) values ( ?, ? )" );
//			ps.setLong( 1, 1 );
//			ps.setString( 2, "name" );
//			jdbcCoord.getResultSetReturn().execute( ps );
//			assertTrue( jdbcCoord.hasRegisteredResources() );
//			assertEquals( 1, observer.getPhysicalConnectionObtainedCount() );
//			assertEquals( 0, observer.getPhysicalConnectionReleasedCount() );
//			jdbcCoord.release( ps );
//			assertFalse( jdbcCoord.hasRegisteredResources() );
//			assertEquals( 1, observer.getPhysicalConnectionObtainedCount() );
//			assertEquals( 1, observer.getPhysicalConnectionReleasedCount() );
//		}
//		catch ( SQLException sqle ) {
//			fail( "incorrect exception type : sqlexception" );
//		}
//		finally {
//			session.close();
//		}
//
//		assertFalse( jdbcCoord.hasRegisteredResources() );
	}
