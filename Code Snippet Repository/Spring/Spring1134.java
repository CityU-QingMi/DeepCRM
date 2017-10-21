	@Test
	public void testHierarchicalResolutionWithOverride() throws Exception {
		Object test3 = this.listableBeanFactory.getBean("test3");
		Object test = this.listableBeanFactory.getBean("test");

		Map<String, ?> beans =
				BeanFactoryUtils.beansOfTypeIncludingAncestors(this.listableBeanFactory, ITestBean.class, true, false);
		assertEquals(2, beans.size());
		assertEquals(test3, beans.get("test3"));
		assertEquals(test, beans.get("test"));

		beans = BeanFactoryUtils.beansOfTypeIncludingAncestors(this.listableBeanFactory, ITestBean.class, false, false);
		assertEquals(1, beans.size());
		assertEquals(test, beans.get("test"));

		beans = BeanFactoryUtils.beansOfTypeIncludingAncestors(this.listableBeanFactory, ITestBean.class, false, true);
		Object testFactory1 = this.listableBeanFactory.getBean("testFactory1");
		assertEquals(2, beans.size());
		assertEquals(test, beans.get("test"));
		assertEquals(testFactory1, beans.get("testFactory1"));

		beans = BeanFactoryUtils.beansOfTypeIncludingAncestors(this.listableBeanFactory, ITestBean.class, true, true);
		assertEquals(4, beans.size());
		assertEquals(test3, beans.get("test3"));
		assertEquals(test, beans.get("test"));
		assertEquals(testFactory1, beans.get("testFactory1"));
		assertTrue(beans.get("testFactory2") instanceof TestBean);

		beans = BeanFactoryUtils.beansOfTypeIncludingAncestors(this.listableBeanFactory, DummyFactory.class, true, true);
		assertEquals(2, beans.size());
		assertEquals(this.listableBeanFactory.getBean("&testFactory1"), beans.get("&testFactory1"));
		assertEquals(this.listableBeanFactory.getBean("&testFactory2"), beans.get("&testFactory2"));

		beans = BeanFactoryUtils.beansOfTypeIncludingAncestors(this.listableBeanFactory, FactoryBean.class, true, true);
		assertEquals(2, beans.size());
		assertEquals(this.listableBeanFactory.getBean("&testFactory1"), beans.get("&testFactory1"));
		assertEquals(this.listableBeanFactory.getBean("&testFactory2"), beans.get("&testFactory2"));
	}
