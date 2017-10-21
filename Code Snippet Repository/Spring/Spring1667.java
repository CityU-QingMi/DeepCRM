	@Test
	public void testNestedInCollections() {
		TestBean bean = (TestBean) this.beanFactory.getBean("nestedCustomTagBean");

		List list = bean.getSomeList();
		assertEquals(1, list.size());
		assertEquals(Integer.MIN_VALUE, list.get(0));

		Set set = bean.getSomeSet();
		assertEquals(2, set.size());
		assertTrue(set.contains(Thread.State.NEW));
		assertTrue(set.contains(Thread.State.RUNNABLE));

		Map map = bean.getSomeMap();
		assertEquals(1, map.size());
		assertEquals(CustomEnum.VALUE_1, map.get("min"));

		TestBean bean2 = (TestBean) this.beanFactory.getBean("nestedCustomTagBean");
		assertEquals(list, bean2.getSomeList());
		assertEquals(set, bean2.getSomeSet());
		assertEquals(map, bean2.getSomeMap());
		assertFalse(list == bean2.getSomeList());
		assertFalse(set == bean2.getSomeSet());
		assertFalse(map == bean2.getSomeMap());
	}
