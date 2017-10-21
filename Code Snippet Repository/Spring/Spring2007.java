	public void testJavaMailSenderWithSimpleMessages() throws MessagingException, IOException {
		MockJavaMailSender sender = new MockJavaMailSender();
		sender.setHost("host");
		sender.setUsername("username");
		sender.setPassword("password");

		SimpleMailMessage simpleMessage1 = new SimpleMailMessage();
		simpleMessage1.setTo("he@mail.org");
		SimpleMailMessage simpleMessage2 = new SimpleMailMessage();
		simpleMessage2.setTo("she@mail.org");
		sender.send(simpleMessage1, simpleMessage2);

		assertEquals("host", sender.transport.getConnectedHost());
		assertEquals("username", sender.transport.getConnectedUsername());
		assertEquals("password", sender.transport.getConnectedPassword());
		assertTrue(sender.transport.isCloseCalled());

		assertEquals(2, sender.transport.getSentMessages().size());
		MimeMessage sentMessage1 = sender.transport.getSentMessage(0);
		List<Address> tos1 = Arrays.asList(sentMessage1.getRecipients(Message.RecipientType.TO));
		assertEquals(1, tos1.size());
		assertEquals("he@mail.org", ((InternetAddress) tos1.get(0)).getAddress());
		MimeMessage sentMessage2 = sender.transport.getSentMessage(1);
		List<Address> tos2 = Arrays.asList(sentMessage2.getRecipients(Message.RecipientType.TO));
		assertEquals(1, tos2.size());
		assertEquals("she@mail.org", ((InternetAddress) tos2.get(0)).getAddress());
	}
