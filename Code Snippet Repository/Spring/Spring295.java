	@Test
	public void testAspectMethodThrowsExceptionLegalOnSignature() {
		TestBean target = new TestBean();
		UnsupportedOperationException expectedException = new UnsupportedOperationException();
		List<Advisor> advisors = getFixture().getAdvisors(new SingletonMetadataAwareAspectInstanceFactory(new ExceptionAspect(expectedException),"someBean"));
		assertEquals("One advice method was found", 1, advisors.size());
		ITestBean itb = (ITestBean) createProxy(target,
				advisors,
				ITestBean.class);
		try {
			itb.getAge();
			fail();
		}
		catch (UnsupportedOperationException ex) {
			assertSame(expectedException, ex);
		}
	}
