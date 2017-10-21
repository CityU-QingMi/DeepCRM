	public SockJsFrame(String content) {
		Assert.hasText(content, "Content must not be empty");
		if ("o".equals(content)) {
			this.type = SockJsFrameType.OPEN;
			this.content = content;
		}
		else if ("h".equals(content)) {
			this.type = SockJsFrameType.HEARTBEAT;
			this.content = content;
		}
		else if (content.charAt(0) == 'a') {
			this.type = SockJsFrameType.MESSAGE;
			this.content = (content.length() > 1 ? content : "a[]");
		}
		else if (content.charAt(0) == 'm') {
			this.type = SockJsFrameType.MESSAGE;
			this.content = (content.length() > 1 ? content : "null");
		}
		else if (content.charAt(0) == 'c') {
			this.type = SockJsFrameType.CLOSE;
			this.content = (content.length() > 1 ? content : "c[]");
		}
		else {
			throw new IllegalArgumentException("Unexpected SockJS frame type in content \"" + content + "\"");
		}
	}
