	@Test
	public void test() {
		assertNotNull("TestRule should have been @Autowired", autowiredTestRule);

		// Rationale for the following assertion:
		//
		// The field value for the custom rule is null when JUnit sees it. JUnit then
		// ignores the null value, and at a later point in time Spring injects the rule
		// from the ApplicationContext and overrides the null field value. But that's too
		// late: JUnit never sees the rule supplied by Spring via dependency injection.
		assertFalse("@Autowired TestRule should NOT have been applied", autowiredTestRule.applied);
	}
