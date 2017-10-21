	private void assertPart(Buffer buffer, String disposition, String boundary, String name,
			String contentType, String value) throws EOFException {

		assertTrue(buffer.readUtf8Line().contains("--" + boundary));
		String line = buffer.readUtf8Line();
		assertTrue(line.contains("Content-Disposition: "+ disposition));
		assertTrue(line.contains("name=\""+ name + "\""));
		assertTrue(buffer.readUtf8Line().startsWith("Content-Type: "+contentType));
		assertTrue(buffer.readUtf8Line().equals("Content-Length: " + value.length()));
		assertTrue(buffer.readUtf8Line().equals(""));
		assertTrue(buffer.readUtf8Line().equals(value));
	}
