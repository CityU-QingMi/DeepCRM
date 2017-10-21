	@Override
	public String encode(String... messages) {
		Assert.notNull(messages, "messages must not be null");
		StringBuilder sb = new StringBuilder();
		sb.append("a[");
		for (int i = 0; i < messages.length; i++) {
			sb.append('"');
			char[] quotedChars = applyJsonQuoting(messages[i]);
			sb.append(escapeSockJsSpecialChars(quotedChars));
			sb.append('"');
            if (i < messages.length - 1) {
                sb.append(',');
            }
		}
		sb.append(']');
		return sb.toString();
	}
