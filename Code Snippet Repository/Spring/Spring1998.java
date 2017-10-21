	@Test
	public void javaMailSenderWithParseExceptionOnMimeMessagePreparator() {
		MockJavaMailSender sender = new MockJavaMailSender();
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				mimeMessage.setFrom(new InternetAddress(""));
			}
		};
		try {
			sender.send(preparator);
		}
		catch (MailParseException ex) {
			// expected
			assertTrue(ex.getCause() instanceof AddressException);
		}
	}
