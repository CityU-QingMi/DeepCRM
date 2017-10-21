	@Test
	public void testEqualsAndHashCode() throws Exception {
		ComposablePointcut pc1 = new ComposablePointcut();
		ComposablePointcut pc2 = new ComposablePointcut();

		assertEquals(pc1, pc2);
		assertEquals(pc1.hashCode(), pc2.hashCode());

		pc1.intersection(GETTER_METHOD_MATCHER);

		assertFalse(pc1.equals(pc2));
		assertFalse(pc1.hashCode() == pc2.hashCode());

		pc2.intersection(GETTER_METHOD_MATCHER);

		assertEquals(pc1, pc2);
		assertEquals(pc1.hashCode(), pc2.hashCode());

		pc1.union(GET_AGE_METHOD_MATCHER);
		pc2.union(GET_AGE_METHOD_MATCHER);

		assertEquals(pc1, pc2);
		assertEquals(pc1.hashCode(), pc2.hashCode());
	}
