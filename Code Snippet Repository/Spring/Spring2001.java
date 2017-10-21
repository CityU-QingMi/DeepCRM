	@Test
	public void failedMailServerConnect() throws Exception {
		MockJavaMailSender sender = new MockJavaMailSender();
		sender.setHost(null);
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
			assertEquals(1, ex.getFailedMessages().size());
			assertSame(simpleMessage1, ex.getFailedMessages().keySet().iterator().next());
			assertSame(ex.getCause(), ex.getFailedMessages().values().iterator().next());
		}
	}
