	private static String decodeHeaderFieldParam(String input) {
		Assert.notNull(input, "Input String should not be null");
		int firstQuoteIndex = input.indexOf("'");
		int secondQuoteIndex = input.indexOf("'", firstQuoteIndex + 1);
		// US_ASCII
		if (firstQuoteIndex == -1 || secondQuoteIndex == -1) {
			return input;
		}
		Charset charset = Charset.forName(input.substring(0, firstQuoteIndex));
		Assert.isTrue(UTF_8.equals(charset) || ISO_8859_1.equals(charset),
				"Charset should be UTF-8 or ISO-8859-1");
		byte[] value = input.substring(secondQuoteIndex + 1, input.length()).getBytes(charset);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int index = 0;
		while (index < value.length) {
			byte b = value[index];
			if (isRFC5987AttrChar(b)) {
				bos.write((char) b);
				index++;
			}
			else if (b == '%') {
				char[] array = { (char)value[index + 1], (char)value[index + 2]};
				bos.write(Integer.parseInt(String.valueOf(array), 16));
				index+=3;
			}
			else {
				throw new IllegalArgumentException("Invalid header field parameter format (as defined in RFC 5987)");
			}
		}
		return new String(bos.toByteArray(), charset);
	}
