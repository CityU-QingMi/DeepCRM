	@Override
	public void write(char[] buffer, int offset, int length) {
		for (int i = 0; i < length; i++) {
			char ch = buffer[offset + i];
			if (ch == '\n' && this.buffer.length() > 0) {
				this.logger.debug(this.buffer.toString());
				this.buffer.setLength(0);
			}
			else {
				this.buffer.append(ch);
			}
		}
	}
