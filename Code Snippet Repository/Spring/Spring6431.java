	@After
	public void verifyResults() {
		assertNotNull(result);
		assertEquals(2, result.size());
		TestBean testBean1 = result.get(0);
		TestBean testBean2 = result.get(1);
		assertEquals("tb1", testBean1.getName());
		assertEquals("tb2", testBean2.getName());
		assertEquals(1, testBean1.getAge());
		assertEquals(2, testBean2.getAge());
	}
