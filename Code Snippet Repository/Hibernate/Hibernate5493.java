	private void testLobCreation(LobCreator lobCreator) throws SQLException{
		Blob blob = lobCreator.createBlob( new byte[] {} );
		if ( lobCreator == NonContextualLobCreator.INSTANCE ) {
			assertTrue( blob instanceof BlobImplementer );
		}
		else {
			assertTrue( blob instanceof JdbcBlob );
		}
		blob = lobCreator.wrap( blob );
		assertTrue( blob instanceof WrappedBlob );

		Clob clob = lobCreator.createClob( "Hi" );
		if ( lobCreator == NonContextualLobCreator.INSTANCE ) {
			assertTrue( clob instanceof ClobImplementer );
		}
		else {
			assertTrue( clob instanceof JdbcClob );
		}
		clob = lobCreator.wrap( clob );
		assertTrue( clob instanceof WrappedClob );

		Clob nclob = lobCreator.createNClob( "Hi" );
		if ( lobCreator == NonContextualLobCreator.INSTANCE ) {
			assertTrue( nclob instanceof NClobImplementer );
		}
		else {
			assertTrue( nclob instanceof JdbcNClob );
		}
		assertTrue( NClob.class.isInstance( nclob ) );
		nclob = lobCreator.wrap( nclob );
		assertTrue( nclob instanceof WrappedClob );

		blob.free();
		clob.free();
		nclob.free();
	}
