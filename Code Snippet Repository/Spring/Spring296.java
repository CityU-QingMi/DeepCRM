	@Test
	public void testAspectMethodThrowsExceptionIllegalOnSignature() {
		TestBean target = new TestBean();
		RemoteException expectedException = new RemoteException();
		List<Advisor> advisors = getFixture().getAdvisors(new SingletonMetadataAwareAspectInstanceFactory(new ExceptionAspect(expectedException),"someBean"));
		assertEquals("One advice method was found", 1, advisors.size());
		ITestBean itb = (ITestBean) createProxy(target,
				advisors,
				ITestBean.class);
		try {
			itb.getAge();
			fail();
		}
		catch (UndeclaredThrowableException ex) {
			assertSame(expectedException, ex.getCause());
		}
	}
