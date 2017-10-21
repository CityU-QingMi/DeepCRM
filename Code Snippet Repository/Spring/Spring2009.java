	@Test
	public void javaMailSenderWithMimeMessages() throws MessagingException {
		MockJavaMailSender sender = new MockJavaMailSender();
		sender.setHost("host");
		sender.setUsername("username");
		sender.setPassword("password");

		MimeMessage mimeMessage1 = sender.createMimeMessage();
		mimeMessage1.setRecipient(Message.RecipientType.TO, new InternetAddress("he@mail.org"));
		MimeMessage mimeMessage2 = sender.createMimeMessage();
		mimeMessage2.setRecipient(Message.RecipientType.TO, new InternetAddress("she@mail.org"));
		sender.send(mimeMessage1, mimeMessage2);

		assertEquals("host", sender.transport.getConnectedHost());
		assertEquals("username", sender.transport.getConnectedUsername());
		assertEquals("password", sender.transport.getConnectedPassword());
		assertTrue(sender.transport.isCloseCalled());
		assertEquals(2, sender.transport.getSentMessages().size());
		assertEquals(mimeMessage1, sender.transport.getSentMessage(0));
		assertEquals(mimeMessage2, sender.transport.getSentMessage(1));
	}
