	protected void assertTestBeanCount(int count) {
		String[] defNames = getListableBeanFactory().getBeanNamesForType(TestBean.class, true, false);
		assertTrue("We should have " + count + " beans for class org.springframework.tests.sample.beans.TestBean, not " +
				defNames.length, defNames.length == count);

		int countIncludingFactoryBeans = count + 2;
		String[] names = getListableBeanFactory().getBeanNamesForType(TestBean.class, true, true);
		assertTrue("We should have " + countIncludingFactoryBeans +
				" beans for class org.springframework.tests.sample.beans.TestBean, not " + names.length,
				names.length == countIncludingFactoryBeans);
	}
