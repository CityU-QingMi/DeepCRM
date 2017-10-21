	@Test
	public void testNamedPointcutFromAspectLibraryWithBinding() {
		TestBean target = new TestBean();
		ITestBean itb = (ITestBean) createProxy(target,
				getFixture().getAdvisors(new SingletonMetadataAwareAspectInstanceFactory(new NamedPointcutAspectFromLibraryWithBinding(),"someBean")),
				ITestBean.class);
		itb.setAge(10);
		assertEquals("Around advice must apply", 20, itb.getAge());
		assertEquals(20,target.getAge());
	}
