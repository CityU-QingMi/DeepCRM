	@Test
	public void readWithExternalReference() throws IOException {
		String body = "<!DOCTYPE MyBean SYSTEM \"http://192.168.28.42/1.jsp\" [" +
				"  <!ELEMENT root ANY >\n" +
				"  <!ENTITY ext SYSTEM \"" +
				new ClassPathResource("external.txt", getClass()).getURI() +
				"\" >]><MyBean><string>&ext;</string></MyBean>";

		MockHttpInputMessage inputMessage = new MockHttpInputMessage(body.getBytes("UTF-8"));
		inputMessage.getHeaders().setContentType(new MediaType("application", "xml"));

		this.thrown.expect(HttpMessageNotReadableException.class);
		this.converter.read(MyBean.class, inputMessage);
	}
