	@Test
	public void testFriendlyErrorOnNoLocation2ArgMatching() {
		AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
		try {
			pc.matches(getAge, ITestBean.class);
			fail();
		}
		catch (IllegalStateException ex) {
			assertTrue(ex.getMessage().contains("expression"));
		}
	}
