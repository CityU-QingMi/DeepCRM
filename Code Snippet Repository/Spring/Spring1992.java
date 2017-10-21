	public final void testEqualsObject() {
		SimpleMailMessage message1;
		SimpleMailMessage message2;

		// Same object is equal
		message1 = new SimpleMailMessage();
		message2 = message1;
		assertTrue(message1.equals(message2));

		// Null object is not equal
		message1 = new SimpleMailMessage();
		message2 = null;
		assertTrue(!(message1.equals(message2)));

		// Different class is not equal
		assertTrue(!(message1.equals(new Object())));

		// Equal values are equal
		message1 = new SimpleMailMessage();
		message2 = new SimpleMailMessage();
		assertTrue(message1.equals(message2));

		message1 = new SimpleMailMessage();
		message1.setFrom("from@somewhere");
		message1.setReplyTo("replyTo@somewhere");
		message1.setTo("to@somewhere");
		message1.setCc("cc@somewhere");
		message1.setBcc("bcc@somewhere");
		message1.setSentDate(new Date());
		message1.setSubject("subject");
		message1.setText("text");
		message2 = new SimpleMailMessage(message1);
		assertTrue(message1.equals(message2));
	}
