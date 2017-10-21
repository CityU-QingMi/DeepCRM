	@Test
	public void testFriendlyErrorOnNoLocationClassMatching() {
		AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
		try {
			pc.matches(ITestBean.class);
			fail();
		}
		catch (IllegalStateException ex) {
			assertTrue(ex.getMessage().contains("expression"));
		}
	}
