	@Test
	public void javaMailSenderWithMimeMessageHelper() throws MessagingException {
		MockJavaMailSender sender = new MockJavaMailSender();
		sender.setHost("host");
		sender.setUsername("username");
		sender.setPassword("password");

		MimeMessageHelper message = new MimeMessageHelper(sender.createMimeMessage());
		assertNull(message.getEncoding());
		assertTrue(message.getFileTypeMap() instanceof ConfigurableMimeFileTypeMap);

		message.setTo("you@mail.org");
		sender.send(message.getMimeMessage());

		assertEquals("host", sender.transport.getConnectedHost());
		assertEquals("username", sender.transport.getConnectedUsername());
		assertEquals("password", sender.transport.getConnectedPassword());
		assertTrue(sender.transport.isCloseCalled());
		assertEquals(1, sender.transport.getSentMessages().size());
		assertEquals(message.getMimeMessage(), sender.transport.getSentMessage(0));
	}
