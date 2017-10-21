	@Test
	public void testDetermineCommonAncestor() {
		assertEquals(Number.class, ClassUtils.determineCommonAncestor(Integer.class, Number.class));
		assertEquals(Number.class, ClassUtils.determineCommonAncestor(Number.class, Integer.class));
		assertEquals(Number.class, ClassUtils.determineCommonAncestor(Number.class, null));
		assertEquals(Integer.class, ClassUtils.determineCommonAncestor(null, Integer.class));
		assertEquals(Integer.class, ClassUtils.determineCommonAncestor(Integer.class, Integer.class));

		assertEquals(Number.class, ClassUtils.determineCommonAncestor(Integer.class, Float.class));
		assertEquals(Number.class, ClassUtils.determineCommonAncestor(Float.class, Integer.class));
		assertNull(ClassUtils.determineCommonAncestor(Integer.class, String.class));
		assertNull(ClassUtils.determineCommonAncestor(String.class, Integer.class));

		assertEquals(Collection.class, ClassUtils.determineCommonAncestor(List.class, Collection.class));
		assertEquals(Collection.class, ClassUtils.determineCommonAncestor(Collection.class, List.class));
		assertEquals(Collection.class, ClassUtils.determineCommonAncestor(Collection.class, null));
		assertEquals(List.class, ClassUtils.determineCommonAncestor(null, List.class));
		assertEquals(List.class, ClassUtils.determineCommonAncestor(List.class, List.class));

		assertNull(ClassUtils.determineCommonAncestor(List.class, Set.class));
		assertNull(ClassUtils.determineCommonAncestor(Set.class, List.class));
		assertNull(ClassUtils.determineCommonAncestor(List.class, Runnable.class));
		assertNull(ClassUtils.determineCommonAncestor(Runnable.class, List.class));

		assertEquals(List.class, ClassUtils.determineCommonAncestor(List.class, ArrayList.class));
		assertEquals(List.class, ClassUtils.determineCommonAncestor(ArrayList.class, List.class));
		assertNull(ClassUtils.determineCommonAncestor(List.class, String.class));
		assertNull(ClassUtils.determineCommonAncestor(String.class, List.class));
	}
