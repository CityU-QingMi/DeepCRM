	@Test
	public void javaMailSenderWithSimpleMessage() throws MessagingException, IOException {
		MockJavaMailSender sender = new MockJavaMailSender();
		sender.setHost("host");
		sender.setPort(30);
		sender.setUsername("username");
		sender.setPassword("password");

		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setFrom("me@mail.org");
		simpleMessage.setReplyTo("reply@mail.org");
		simpleMessage.setTo("you@mail.org");
		simpleMessage.setCc(new String[] {"he@mail.org", "she@mail.org"});
		simpleMessage.setBcc(new String[] {"us@mail.org", "them@mail.org"});
		Date sentDate = new GregorianCalendar(2004, 1, 1).getTime();
		simpleMessage.setSentDate(sentDate);
		simpleMessage.setSubject("my subject");
		simpleMessage.setText("my text");
		sender.send(simpleMessage);

		assertEquals("host", sender.transport.getConnectedHost());
		assertEquals(30, sender.transport.getConnectedPort());
		assertEquals("username", sender.transport.getConnectedUsername());
		assertEquals("password", sender.transport.getConnectedPassword());
		assertTrue(sender.transport.isCloseCalled());

		assertEquals(1, sender.transport.getSentMessages().size());
		MimeMessage sentMessage = sender.transport.getSentMessage(0);
		List<Address> froms = Arrays.asList(sentMessage.getFrom());
		assertEquals(1, froms.size());
		assertEquals("me@mail.org", ((InternetAddress) froms.get(0)).getAddress());
		List<Address> replyTos = Arrays.asList(sentMessage.getReplyTo());
		assertEquals("reply@mail.org", ((InternetAddress) replyTos.get(0)).getAddress());
		List<Address> tos = Arrays.asList(sentMessage.getRecipients(Message.RecipientType.TO));
		assertEquals(1, tos.size());
		assertEquals("you@mail.org", ((InternetAddress) tos.get(0)).getAddress());
		List<Address> ccs = Arrays.asList(sentMessage.getRecipients(Message.RecipientType.CC));
		assertEquals(2, ccs.size());
		assertEquals("he@mail.org", ((InternetAddress) ccs.get(0)).getAddress());
		assertEquals("she@mail.org", ((InternetAddress) ccs.get(1)).getAddress());
		List<Address> bccs = Arrays.asList(sentMessage.getRecipients(Message.RecipientType.BCC));
		assertEquals(2, bccs.size());
		assertEquals("us@mail.org", ((InternetAddress) bccs.get(0)).getAddress());
		assertEquals("them@mail.org", ((InternetAddress) bccs.get(1)).getAddress());
		assertEquals(sentDate.getTime(), sentMessage.getSentDate().getTime());
		assertEquals("my subject", sentMessage.getSubject());
		assertEquals("my text", sentMessage.getContent());
	}
