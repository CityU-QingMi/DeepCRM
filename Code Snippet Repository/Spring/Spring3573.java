	@Test
	public void getMessageWithMessageAlreadyLookedFor() {
		Object[] arguments = {
			new Integer(7), new Date(System.currentTimeMillis()),
			"a disturbance in the Force"
		};

		// The first time searching, we don't care about for this test
		// Try with Locale.US
		sac.getMessage("message.format.example1", arguments, Locale.US);

		// Now msg better be as expected
		assertTrue("2nd search within MsgFormat cache returned expected message for Locale.US",
				sac.getMessage("message.format.example1", arguments, Locale.US).indexOf(
						"there was \"a disturbance in the Force\" on planet 7.") != -1);

		Object[] newArguments = {
			new Integer(8), new Date(System.currentTimeMillis()),
			"a disturbance in the Force"
		};

		// Now msg better be as expected even with different args
		assertTrue("2nd search within MsgFormat cache with different args returned expected message for Locale.US",
				sac.getMessage("message.format.example1", newArguments, Locale.US)
				.indexOf("there was \"a disturbance in the Force\" on planet 8.") != -1);
	}
