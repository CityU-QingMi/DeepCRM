	public boolean hasEmptyMessageBody() throws IOException {
		InputStream body = this.response.getBody();
		if (body.markSupported()) {
			body.mark(1);
			if (body.read() == -1) {
				return true;
			}
			else {
				body.reset();
				return false;
			}
		}
		else {
			this.pushbackInputStream = new PushbackInputStream(body);
			int b = this.pushbackInputStream.read();
			if (b == -1) {
				return true;
			}
			else {
				this.pushbackInputStream.unread(b);
				return false;
			}
		}
	}
