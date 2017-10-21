	private void testNamedPointcuts(Object aspectInstance) {
		TestBean target = new TestBean();
		int realAge = 65;
		target.setAge(realAge);
		ITestBean itb = (ITestBean) createProxy(target,
				getFixture().getAdvisors(new SingletonMetadataAwareAspectInstanceFactory(aspectInstance,"someBean")),
				ITestBean.class);
		assertEquals("Around advice must apply", -1, itb.getAge());
		assertEquals(realAge, target.getAge());
	}
