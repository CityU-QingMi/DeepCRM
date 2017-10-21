	@Test
	public void getMessageWithNoDefaultPassedInAndFoundInMsgCatalog() {
		Object[] arguments = {
			new Integer(7), new Date(System.currentTimeMillis()),
			"a disturbance in the Force"
		};

/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
		assertTrue("msg from staticMsgSource for Locale.US substituting args for placeholders is as expected",
				sac.getMessage("message.format.example1", arguments, Locale.US)
				.indexOf("there was \"a disturbance in the Force\" on planet 7.") != -1);

		// Try with Locale.UK
		assertTrue("msg from staticMsgSource for Locale.UK substituting args for placeholders is as expected",
				sac.getMessage("message.format.example1", arguments, Locale.UK)
				.indexOf("there was \"a disturbance in the Force\" on station number 7.") != -1);

		// Try with Locale.US - Use a different test msg that requires no args
		assertTrue("msg from staticMsgSource for Locale.US that requires no args is as expected",
				sac.getMessage("message.format.example2", null, Locale.US)
				.equals("This is a test message in the message catalog with no args."));
	}
