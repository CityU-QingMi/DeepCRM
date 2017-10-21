	@Test
	public void testClassNamesToString() {
		List ifcs = new LinkedList();
		ifcs.add(Serializable.class);
		ifcs.add(Runnable.class);
		assertEquals("[interface java.io.Serializable, interface java.lang.Runnable]", ifcs.toString());
		assertEquals("[java.io.Serializable, java.lang.Runnable]", ClassUtils.classNamesToString(ifcs));

		List classes = new LinkedList();
		classes.add(LinkedList.class);
		classes.add(Integer.class);
		assertEquals("[class java.util.LinkedList, class java.lang.Integer]", classes.toString());
		assertEquals("[java.util.LinkedList, java.lang.Integer]", ClassUtils.classNamesToString(classes));

		assertEquals("[interface java.util.List]", Collections.singletonList(List.class).toString());
		assertEquals("[java.util.List]", ClassUtils.classNamesToString(List.class));

		assertEquals("[]", Collections.EMPTY_LIST.toString());
		assertEquals("[]", ClassUtils.classNamesToString(Collections.EMPTY_LIST));
	}
