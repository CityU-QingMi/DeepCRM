	@Test
	public void setCollectionPropertyWithStringValueAndCustomEditor() {
		IndexedTestBean target = new IndexedTestBean();
		AbstractPropertyAccessor accessor = createAccessor(target);
		accessor.registerCustomEditor(String.class, "set", new StringTrimmerEditor(false));
		accessor.registerCustomEditor(String.class, "list", new StringTrimmerEditor(false));

		accessor.setPropertyValue("set", "set1 ");
		accessor.setPropertyValue("sortedSet", "sortedSet1");
		accessor.setPropertyValue("list", "list1 ");
		assertEquals(1, target.getSet().size());
		assertTrue(target.getSet().contains("set1"));
		assertEquals(1, target.getSortedSet().size());
		assertTrue(target.getSortedSet().contains("sortedSet1"));
		assertEquals(1, target.getList().size());
		assertTrue(target.getList().contains("list1"));

		accessor.setPropertyValue("list", Collections.singletonList("list1 "));
		assertTrue(target.getList().contains("list1"));
	}
