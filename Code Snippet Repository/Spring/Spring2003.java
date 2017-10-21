	@Test
	public void failedSimpleMessage() throws Exception {
		MockJavaMailSender sender = new MockJavaMailSender();
		sender.setHost("host");
		sender.setUsername("username");
		sender.setPassword("password");

		SimpleMailMessage simpleMessage1 = new SimpleMailMessage();
		simpleMessage1.setTo("he@mail.org");
		simpleMessage1.setSubject("fail");
		SimpleMailMessage simpleMessage2 = new SimpleMailMessage();
		simpleMessage2.setTo("she@mail.org");

		try {
			sender.send(simpleMessage1, simpleMessage2);
		}
		catch (MailSendException ex) {
			ex.printStackTrace();
			assertEquals("host", sender.transport.getConnectedHost());
			assertEquals("username", sender.transport.getConnectedUsername());
			assertEquals("password", sender.transport.getConnectedPassword());
			assertTrue(sender.transport.isCloseCalled());
			assertEquals(1, sender.transport.getSentMessages().size());
			assertEquals(new InternetAddress("she@mail.org"), sender.transport.getSentMessage(0).getAllRecipients()[0]);
			assertEquals(1, ex.getFailedMessages().size());
			assertEquals(simpleMessage1, ex.getFailedMessages().keySet().iterator().next());
			Object subEx = ex.getFailedMessages().values().iterator().next();
			assertTrue(subEx instanceof MessagingException);
			assertEquals("failed", ((MessagingException) subEx).getMessage());
		}
	}
