	@Test
	@TestForIssue(jiraKey = "")
	public void testEvictAll() throws Exception {
		withQueryRegion((sessionFactory, region) -> {
			withSession(sessionFactory, s -> region.put(s, KEY, VALUE1));
			withSession(sessionFactory, s -> assertEquals(VALUE1, region.get(s, KEY)));
			region.evictAll();
			withSession(sessionFactory, s -> assertNull(region.get(s, KEY)));
			assertEquals(Collections.EMPTY_MAP, region.toMap());
		});
	}
