	private int findRegexStart(char[] data, int offset) {
		int pos = offset;
		while (pos < data.length) {
			if (data[pos] == ':') {
				return pos + 1;
			}
			pos++;
		}
		return -1;
	}
