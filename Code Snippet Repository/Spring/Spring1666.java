	@Test
	public void testNestedShortcutCollections() {
		TestBean bean = (TestBean) this.beanFactory.getBean("nestedShortcutCollections");

		assertEquals(1, bean.getStringArray().length);
		assertEquals("fooStr", bean.getStringArray()[0]);

		List list = bean.getSomeList();
		assertEquals(1, list.size());
		assertEquals("foo", list.get(0));

		Set set = bean.getSomeSet();
		assertEquals(1, set.size());
		assertTrue(set.contains("bar"));

		TestBean bean2 = (TestBean) this.beanFactory.getBean("nestedShortcutCollections");
		assertTrue(Arrays.equals(bean.getStringArray(), bean2.getStringArray()));
		assertFalse(bean.getStringArray() == bean2.getStringArray());
		assertEquals(list, bean2.getSomeList());
		assertEquals(set, bean2.getSomeSet());
		assertFalse(list == bean2.getSomeList());
		assertFalse(set == bean2.getSomeSet());
	}
