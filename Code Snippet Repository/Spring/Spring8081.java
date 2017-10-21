	@Test
	public void marshalAttachments() throws Exception {
		marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(BinaryObject.class);
		marshaller.setMtomEnabled(true);
		marshaller.afterPropertiesSet();
		MimeContainer mimeContainer = mock(MimeContainer.class);

		Resource logo = new ClassPathResource("spring-ws.png", getClass());
		DataHandler dataHandler = new DataHandler(new FileDataSource(logo.getFile()));

		given(mimeContainer.convertToXopPackage()).willReturn(true);
		byte[] bytes = FileCopyUtils.copyToByteArray(logo.getInputStream());
		BinaryObject object = new BinaryObject(bytes, dataHandler);
		StringWriter writer = new StringWriter();
		marshaller.marshal(object, new StreamResult(writer), mimeContainer);
		assertTrue("No XML written", writer.toString().length() > 0);
		verify(mimeContainer, times(3)).addAttachment(isA(String.class), isA(DataHandler.class));
	}
