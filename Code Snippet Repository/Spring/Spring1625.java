	@Test
	public void mergeSetWithInnerBeanAsSetElementInConstructor() throws Exception {
		TestBean bean = (TestBean) this.beanFactory.getBean("childWithSetOfRefsInConstructor");
		Set set = bean.getSomeSet();
		assertNotNull(set);
		assertEquals(2, set.size());
		Iterator it = set.iterator();
		it.next();
		Object o = it.next();
		assertNotNull(o);
		assertTrue(o instanceof TestBean);
		assertEquals("Sally", ((TestBean) o).getName());
	}
