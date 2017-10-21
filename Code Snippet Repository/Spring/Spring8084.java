	@Test
	public void marshalAttachments() throws Exception {
		unmarshaller = new Jaxb2Marshaller();
		unmarshaller.setClassesToBeBound(BinaryObject.class);
		unmarshaller.setMtomEnabled(true);
		unmarshaller.afterPropertiesSet();
		MimeContainer mimeContainer = mock(MimeContainer.class);

		Resource logo = new ClassPathResource("spring-ws.png", getClass());
		DataHandler dataHandler = new DataHandler(new FileDataSource(logo.getFile()));

		given(mimeContainer.isXopPackage()).willReturn(true);
		given(mimeContainer.getAttachment("<6b76528d-7a9c-4def-8e13-095ab89e9bb7@http://springframework.org/spring-ws>")).willReturn(dataHandler);
		given(mimeContainer.getAttachment("<99bd1592-0521-41a2-9688-a8bfb40192fb@http://springframework.org/spring-ws>")).willReturn(dataHandler);
		given(mimeContainer.getAttachment("696cfb9a-4d2d-402f-bb5c-59fa69e7f0b3@spring-ws.png")).willReturn(dataHandler);
		String content = "<binaryObject xmlns='http://springframework.org/spring-ws'>" + "<bytes>" +
				"<xop:Include href='cid:6b76528d-7a9c-4def-8e13-095ab89e9bb7@http://springframework.org/spring-ws' xmlns:xop='http://www.w3.org/2004/08/xop/include'/>" +
				"</bytes>" + "<dataHandler>" +
				"<xop:Include href='cid:99bd1592-0521-41a2-9688-a8bfb40192fb@http://springframework.org/spring-ws' xmlns:xop='http://www.w3.org/2004/08/xop/include'/>" +
				"</dataHandler>" +
				"<swaDataHandler>696cfb9a-4d2d-402f-bb5c-59fa69e7f0b3@spring-ws.png</swaDataHandler>" +
				"</binaryObject>";

		StringReader reader = new StringReader(content);
		Object result = unmarshaller.unmarshal(new StreamSource(reader), mimeContainer);
		assertTrue("Result is not a BinaryObject", result instanceof BinaryObject);
		BinaryObject object = (BinaryObject) result;
		assertNotNull("bytes property not set", object.getBytes());
		assertTrue("bytes property not set", object.getBytes().length > 0);
		assertNotNull("datahandler property not set", object.getSwaDataHandler());
	}
