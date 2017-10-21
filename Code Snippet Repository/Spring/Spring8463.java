		@Test
		public void nestedTest() throws Exception {
			// Note: the following would fail since TestExecutionListeners in
			// the Spring TestContext Framework are not applied to the enclosing
			// instance of an inner test class.
			//
			// assertEquals("foo", foo);

			assertNull("@Autowired field in enclosing instance should be null.", foo);
			assertEquals("bar", bar);
		}
