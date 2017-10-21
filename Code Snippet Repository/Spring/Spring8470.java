	@Test
	public void testSPR_9051() throws Exception {
		assertNotNull(enigma);
		assertNotNull(lifecycleBean);
		assertTrue(lifecycleBean.isInitialized());
		Set<String> names = new HashSet<>();
		names.add(enigma.toString());
		names.add(lifecycleBean.getName());
		assertEquals(names, new HashSet<>(Arrays.asList("enigma #1", "enigma #2")));
	}
