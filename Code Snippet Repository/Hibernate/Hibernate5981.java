	@Test
	@TestForIssue( jiraKey = "" )
	public void testDuplicateFilterExplodedJarExpected() throws Exception {
//		File explodedPar = buildExplodedPar();
//		addPackageToClasspath( explodedPar );
//
//		Filter[] filters = getFilters();
//		Filter[] dupeFilters = new Filter[filters.length * 2];
//		int index = 0;
//		for ( Filter filter : filters ) {
//			dupeFilters[index++] = filter;
//		}
//		filters = getFilters();
//		for ( Filter filter : filters ) {
//			dupeFilters[index++] = filter;
//		}
//		String dirPath = explodedPar.toURL().toExternalForm();
//		// TODO - shouldn't  ExplodedJarVisitor take care of a trailing slash?
//		if ( dirPath.endsWith( "/" ) ) {
//			dirPath = dirPath.substring( 0, dirPath.length() - 1 );
//		}
//		JarVisitor jarVisitor = new ExplodedJarVisitor( dirPath, dupeFilters );
//		assertEquals( "explodedpar", jarVisitor.getUnqualifiedJarName() );
//		Set[] entries = jarVisitor.getMatchingEntries();
//		assertEquals( 1, entries[1].size() );
//		assertEquals( 1, entries[0].size() );
//		assertEquals( 1, entries[2].size() );
//		for ( Entry entry : ( Set<Entry> ) entries[2] ) {
//			InputStream is = entry.getInputStream();
//			if ( is != null ) {
//				assertTrue( 0 < is.available() );
//				is.close();
//			}
//		}
//		for ( Entry entry : ( Set<Entry> ) entries[5] ) {
//			InputStream is = entry.getInputStream();
//			if ( is != null ) {
//				assertTrue( 0 < is.available() );
//				is.close();
//			}
//		}
//
//		Entry entry = new Entry( Carpet.class.getName(), null );
//		assertTrue( entries[1].contains( entry ) );
	}
