	public String formatMessage(Object... inserts) {
		StringBuilder formattedMessage = new StringBuilder();
		formattedMessage.append("EL").append(this.code);
		switch (this.kind) {
			case ERROR:
				formattedMessage.append("E");
				break;
		}
		formattedMessage.append(": ");
		formattedMessage.append(MessageFormat.format(this.message, inserts));
		return formattedMessage.toString();
	}
