	@Test
	public void testMapWithLiteralsReferencesAndList() throws Exception {
		HasMap hasMap = (HasMap) this.beanFactory.getBean("mixedMapWithList");
		assertTrue(hasMap.getMap().size() == 4);
		assertTrue(hasMap.getMap().get(null).equals("bar"));
		TestBean jenny = (TestBean) this.beanFactory.getBean("jenny");
		assertTrue(hasMap.getMap().get("jenny").equals(jenny));

		// Check list
		List l = (List) hasMap.getMap().get("list");
		assertNotNull(l);
		assertTrue(l.size() == 4);
		assertTrue(l.get(0).equals("zero"));
		assertTrue(l.get(3) == null);

		// Check nested map in list
		Map m = (Map) l.get(1);
		assertNotNull(m);
		assertTrue(m.size() == 2);
		assertTrue(m.get("fo").equals("bar"));
		assertTrue("Map element 'jenny' should be equal to jenny bean, not " + m.get("jen"),
				m.get("jen").equals(jenny));

		// Check nested list in list
		l = (List) l.get(2);
		assertNotNull(l);
		assertTrue(l.size() == 2);
		assertTrue(l.get(0).equals(jenny));
		assertTrue(l.get(1).equals("ba"));

		// Check nested map
		m = (Map) hasMap.getMap().get("map");
		assertNotNull(m);
		assertTrue(m.size() == 2);
		assertTrue(m.get("foo").equals("bar"));
		assertTrue("Map element 'jenny' should be equal to jenny bean, not " + m.get("jenny"),
				m.get("jenny").equals(jenny));
	}
