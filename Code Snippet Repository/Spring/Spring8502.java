	@Test
	public void customizeContext() throws Exception {

		final StringBuilder builder = new StringBuilder();
		final String expectedContents = "customizeContext() was called";

		new GenericXmlContextLoader() {

			@Override
			protected void customizeContext(GenericApplicationContext context) {
				assertFalse("The context should not yet have been refreshed.", context.isActive());
				builder.append(expectedContents);
			}
		}.loadContext("classpath:/org/springframework/test/context/support/CustomizedGenericXmlContextLoaderTests-context.xml");

		assertEquals("customizeContext() should have been called.", expectedContents, builder.toString());
	}
