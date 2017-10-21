	@Test
	public void testFriendlyErrorOnNoLocation3ArgMatching() {
		AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
		try {
			pc.matches(getAge, ITestBean.class, (Object[]) null);
			fail();
		}
		catch (IllegalStateException ex) {
			assertTrue(ex.getMessage().contains("expression"));
		}
	}
