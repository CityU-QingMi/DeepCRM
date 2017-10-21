	@Test
	public void testNestedCollections() {
		TestBean bean = (TestBean) this.beanFactory.getBean("nestedCollectionsBean");

		List list = bean.getSomeList();
		assertEquals(1, list.size());
		assertEquals("foo", list.get(0));

		Set set = bean.getSomeSet();
		assertEquals(1, set.size());
		assertTrue(set.contains("bar"));

		Map map = bean.getSomeMap();
		assertEquals(1, map.size());
		assertTrue(map.get("foo") instanceof Set);
		Set innerSet = (Set) map.get("foo");
		assertEquals(1, innerSet.size());
		assertTrue(innerSet.contains("bar"));

		TestBean bean2 = (TestBean) this.beanFactory.getBean("nestedCollectionsBean");
		assertEquals(list, bean2.getSomeList());
		assertEquals(set, bean2.getSomeSet());
		assertEquals(map, bean2.getSomeMap());
		assertFalse(list == bean2.getSomeList());
		assertFalse(set == bean2.getSomeSet());
		assertFalse(map == bean2.getSomeMap());
	}
