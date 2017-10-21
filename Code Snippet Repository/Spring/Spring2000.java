	@Test
	public void javaMailProperties() throws MessagingException {
		Properties props = new Properties();
		props.setProperty("bogusKey", "bogusValue");
		MockJavaMailSender sender = new MockJavaMailSender() {
			@Override
			protected Transport getTransport(Session sess) throws NoSuchProviderException {
				assertEquals("bogusValue", sess.getProperty("bogusKey"));
				return super.getTransport(sess);
			}
		};
		sender.setJavaMailProperties(props);
		sender.setHost("host");
		sender.setUsername("username");
		sender.setPassword("password");

		MimeMessage mimeMessage = sender.createMimeMessage();
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("you@mail.org"));
		sender.send(mimeMessage);

		assertEquals("host", sender.transport.getConnectedHost());
		assertEquals("username", sender.transport.getConnectedUsername());
		assertEquals("password", sender.transport.getConnectedPassword());
		assertTrue(sender.transport.isCloseCalled());
		assertEquals(1, sender.transport.getSentMessages().size());
		assertEquals(mimeMessage, sender.transport.getSentMessage(0));
	}
