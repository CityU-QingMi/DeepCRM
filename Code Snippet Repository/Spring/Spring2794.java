	@Test
	public void testIntroductionThrowsUncheckedException() throws Throwable {
		TestBean target = new TestBean();
		target.setAge(21);
		ProxyFactory pc = new ProxyFactory(target);

		@SuppressWarnings("serial")
		class MyDi extends DelegatingIntroductionInterceptor implements TimeStamped {
/**/
/**/
/**/
			@Override
			public long getTimeStamp() {
				throw new UnsupportedOperationException();
			}
		}
		pc.addAdvisor(new DefaultIntroductionAdvisor(new MyDi()));

		TimeStamped ts = (TimeStamped) createProxy(pc);
		try {
			ts.getTimeStamp();
			fail("Should throw UnsupportedOperationException");
		}
		catch (UnsupportedOperationException ex) {
		}
	}
