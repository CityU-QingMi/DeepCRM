	private void assertFilePart(Buffer buffer, String disposition, String boundary, String name,
			String filename, String contentType) throws EOFException {

		assertTrue(buffer.readUtf8Line().contains("--" + boundary));
		String line = buffer.readUtf8Line();
		assertTrue(line.contains("Content-Disposition: "+ disposition));
		assertTrue(line.contains("name=\""+ name + "\""));
		assertTrue(line.contains("filename=\""+ filename + "\""));
		assertTrue(buffer.readUtf8Line().startsWith("Content-Type: "+contentType));
		assertTrue(buffer.readUtf8Line().startsWith("Content-Length: "));
		assertTrue(buffer.readUtf8Line().equals(""));
		assertNotNull(buffer.readUtf8Line());
	}
