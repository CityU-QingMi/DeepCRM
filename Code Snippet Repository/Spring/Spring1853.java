	public SimpleMailMessage(SimpleMailMessage original) {
		Assert.notNull(original, "'original' message argument must not be null");
		this.from = original.getFrom();
		this.replyTo = original.getReplyTo();
		if (original.getTo() != null) {
			this.to = copy(original.getTo());
		}
		if (original.getCc() != null) {
			this.cc = copy(original.getCc());
		}
		if (original.getBcc() != null) {
			this.bcc = copy(original.getBcc());
		}
		this.sentDate = original.getSentDate();
		this.subject = original.getSubject();
		this.text = original.getText();
	}
