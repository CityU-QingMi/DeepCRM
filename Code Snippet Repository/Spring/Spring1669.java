	@Test
	public void testCircularCollectionBeansStartingWithList() {
		this.beanFactory.getBean("circularList");
		TestBean bean = (TestBean) this.beanFactory.getBean("circularCollectionBeansBean");

		List list = bean.getSomeList();
		assertTrue(Proxy.isProxyClass(list.getClass()));
		assertEquals(1, list.size());
		assertEquals(bean, list.get(0));

		Set set = bean.getSomeSet();
		assertFalse(Proxy.isProxyClass(set.getClass()));
		assertEquals(1, set.size());
		assertTrue(set.contains(bean));

		Map map = bean.getSomeMap();
		assertFalse(Proxy.isProxyClass(map.getClass()));
		assertEquals(1, map.size());
		assertEquals(bean, map.get("foo"));
	}
