	@Test
	public void javaMailSenderWithMimeMessagePreparators() {
		MockJavaMailSender sender = new MockJavaMailSender();
		sender.setHost("host");
		sender.setUsername("username");
		sender.setPassword("password");

		final List<Message> messages = new ArrayList<>();

		MimeMessagePreparator preparator1 = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("he@mail.org"));
				messages.add(mimeMessage);
			}
		};
		MimeMessagePreparator preparator2 = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("she@mail.org"));
				messages.add(mimeMessage);
			}
		};
		sender.send(preparator1, preparator2);

		assertEquals("host", sender.transport.getConnectedHost());
		assertEquals("username", sender.transport.getConnectedUsername());
		assertEquals("password", sender.transport.getConnectedPassword());
		assertTrue(sender.transport.isCloseCalled());
		assertEquals(2, sender.transport.getSentMessages().size());
		assertEquals(messages.get(0), sender.transport.getSentMessage(0));
		assertEquals(messages.get(1), sender.transport.getSentMessage(1));
	}
