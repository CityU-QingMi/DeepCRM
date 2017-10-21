	@Test
	public void testIsEmpty() {
		assertTrue(CollectionUtils.isEmpty((Set<Object>) null));
		assertTrue(CollectionUtils.isEmpty((Map<String, String>) null));
		assertTrue(CollectionUtils.isEmpty(new HashMap<String, String>()));
		assertTrue(CollectionUtils.isEmpty(new HashSet<>()));

		List<Object> list = new LinkedList<>();
		list.add(new Object());
		assertFalse(CollectionUtils.isEmpty(list));

		Map<String, String> map = new HashMap<>();
		map.put("foo", "bar");
		assertFalse(CollectionUtils.isEmpty(map));
	}
