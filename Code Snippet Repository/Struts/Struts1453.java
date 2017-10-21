	public void testFinder() {
		ClassPathFinder finder = new ClassPathFinder();
		finder.setPattern("**/xwork-test-wildcard-*.xml");
		Vector<String> found = finder.findMatches();
		assertEquals(found.contains("com/opensymphony/xwork2/config/providers/xwork-test-wildcard-1.xml"), true );
		assertEquals(found.contains("com/opensymphony/xwork2/config/providers/xwork-test-wildcard-2.xml"), true );
		assertEquals(found.contains("com/opensymphony/xwork2/config/providers/xwork-test-wildcard-include.xml"), true );
		assertEquals(found.contains("com/opensymphony/xwork2/config/providers/xwork-test-results.xml"), false);
		
		ClassPathFinder finder2 = new ClassPathFinder();
		finder2.setPattern("com/*/xwork2/config/providers/xwork-test-wildcard-1.xml");
		Vector<String> found2 = finder2.findMatches();
		assertEquals(found2.contains("com/opensymphony/xwork2/config/providers/xwork-test-wildcard-1.xml"), true);
		assertEquals(found2.contains("com/opensymphony/xwork2/config/providers/xwork-test-wildcard-2.xml"), false);
		
		ClassPathFinder finder3 = new ClassPathFinder();
		finder3.setPattern("com/opensymphony/xwork2/config/providers/xwork-test-wildcard-1.xml");
		Vector<String> found3 = finder3.findMatches();
		assertEquals(found3.contains("com/opensymphony/xwork2/config/providers/xwork-test-wildcard-1.xml"), true);
		assertEquals(found3.contains("com/opensymphony/xwork2/config/providers/xwork-test-wildcard-2.xml"), false);
		
		ClassPathFinder finder4 = new ClassPathFinder();
		finder4.setPattern("no/matches/*");
		Vector<String> found4 = finder4.findMatches();
		assertEquals(found4.isEmpty(), true);
		
	}
