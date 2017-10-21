	@Test
	public void javaMailSenderWithCustomSession() throws MessagingException {
		final Session session = Session.getInstance(new Properties());
		MockJavaMailSender sender = new MockJavaMailSender() {
			@Override
			protected Transport getTransport(Session sess) throws NoSuchProviderException {
				assertEquals(session, sess);
				return super.getTransport(sess);
			}
		};
		sender.setSession(session);
		sender.setHost("host");
		sender.setUsername("username");
		sender.setPassword("password");

		MimeMessage mimeMessage = sender.createMimeMessage();
		mimeMessage.setSubject("custom");
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("you@mail.org"));
		mimeMessage.setSentDate(new GregorianCalendar(2005, 3, 1).getTime());
		sender.send(mimeMessage);

		assertEquals("host", sender.transport.getConnectedHost());
		assertEquals("username", sender.transport.getConnectedUsername());
		assertEquals("password", sender.transport.getConnectedPassword());
		assertTrue(sender.transport.isCloseCalled());
		assertEquals(1, sender.transport.getSentMessages().size());
		assertEquals(mimeMessage, sender.transport.getSentMessage(0));
	}
