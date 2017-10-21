	@Test
	public void javaMailSenderWithParseExceptionOnSimpleMessage() {
		MockJavaMailSender sender = new MockJavaMailSender();
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setFrom("");
		try {
			sender.send(simpleMessage);
		}
		catch (MailParseException ex) {
			// expected
			assertTrue(ex.getCause() instanceof AddressException);
		}
	}
