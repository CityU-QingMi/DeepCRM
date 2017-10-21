	@Test
	public void testImportQueryFromMappedSuperclass() {
		Session s = openSession();
		try {
			s.getNamedQuery( "night.olderThan" );
		}
		catch ( MappingException ex ) {
			fail( "Query imported from MappedSuperclass" );
		}
		s.close();
	}
