	@Test
	public void javaMailSenderWithMimeMessageHelperAndDefaultEncoding() throws MessagingException {
		MockJavaMailSender sender = new MockJavaMailSender();
		sender.setHost("host");
		sender.setUsername("username");
		sender.setPassword("password");
		sender.setDefaultEncoding("UTF-8");

		FileTypeMap fileTypeMap = new ConfigurableMimeFileTypeMap();
		sender.setDefaultFileTypeMap(fileTypeMap);
		MimeMessageHelper message = new MimeMessageHelper(sender.createMimeMessage());
		assertEquals("UTF-8", message.getEncoding());
		assertEquals(fileTypeMap, message.getFileTypeMap());

		message.setTo("you@mail.org");
		sender.send(message.getMimeMessage());

		assertEquals("host", sender.transport.getConnectedHost());
		assertEquals("username", sender.transport.getConnectedUsername());
		assertEquals("password", sender.transport.getConnectedPassword());
		assertTrue(sender.transport.isCloseCalled());
		assertEquals(1, sender.transport.getSentMessages().size());
		assertEquals(message.getMimeMessage(), sender.transport.getSentMessage(0));
	}
