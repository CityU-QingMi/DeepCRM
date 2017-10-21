	@Test
	public void failedMailServerClose() throws Exception {
		MockJavaMailSender sender = new MockJavaMailSender();
		sender.setHost("");
		sender.setUsername("username");
		sender.setPassword("password");
		SimpleMailMessage simpleMessage1 = new SimpleMailMessage();
		try {
			sender.send(simpleMessage1);
			fail("Should have thrown MailSendException");
		}
		catch (MailSendException ex) {
			// expected
			ex.printStackTrace();
			assertTrue(ex.getFailedMessages() != null);
			assertEquals(0, ex.getFailedMessages().size());
		}
	}
