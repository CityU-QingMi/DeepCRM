	public void copyTo(MailMessage target) {
		Assert.notNull(target, "The 'target' message argument cannot be null");
		if (getFrom() != null) {
			target.setFrom(getFrom());
		}
		if (getReplyTo() != null) {
			target.setReplyTo(getReplyTo());
		}
		if (getTo() != null) {
			target.setTo(getTo());
		}
		if (getCc() != null) {
			target.setCc(getCc());
		}
		if (getBcc() != null) {
			target.setBcc(getBcc());
		}
		if (getSentDate() != null) {
			target.setSentDate(getSentDate());
		}
		if (getSubject() != null) {
			target.setSubject(getSubject());
		}
		if (getText() != null) {
			target.setText(getText());
		}
	}
