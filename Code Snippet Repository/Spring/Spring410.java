	@Test
	public void testEqualsAndHashCode() throws Exception {
		NameMatchMethodPointcut pc1 = new NameMatchMethodPointcut();
		NameMatchMethodPointcut pc2 = new NameMatchMethodPointcut();

		String foo = "foo";

		assertEquals(pc1, pc2);
		assertEquals(pc1.hashCode(), pc2.hashCode());

		pc1.setMappedName(foo);
		assertFalse(pc1.equals(pc2));
		assertTrue(pc1.hashCode() != pc2.hashCode());

		pc2.setMappedName(foo);
		assertEquals(pc1, pc2);
		assertEquals(pc1.hashCode(), pc2.hashCode());
	}
