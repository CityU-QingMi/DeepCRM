	@Test
	public void testTwoAdvicesOnOneAspect() {
		TestBean target = new TestBean();

		TwoAdviceAspect twoAdviceAspect = new TwoAdviceAspect();
		List<Advisor> advisors = getFixture().getAdvisors(new SingletonMetadataAwareAspectInstanceFactory(twoAdviceAspect,"someBean"));
		assertEquals("Two advice methods found", 2, advisors.size());
		ITestBean itb = (ITestBean) createProxy(target,
				advisors,
				ITestBean.class);
		itb.setName("");
		assertEquals(0, itb.getAge());
		int newAge = 32;
		itb.setAge(newAge);
		assertEquals(1, itb.getAge());
	}
