	@Test
	public void converters() throws Exception {
		marshaller.setConverters(new Converter[]{new EncodedByteArrayConverter()});
		byte[] buf = new byte[]{0x1, 0x2};
		Writer writer = new StringWriter();
		marshaller.marshal(buf, new StreamResult(writer));
		assertThat(writer.toString(), isSimilarTo("<byte-array>AQI=</byte-array>"));
		Reader reader = new StringReader(writer.toString());
		byte[] bufResult = (byte[]) marshaller.unmarshal(new StreamSource(reader));
		assertTrue("Invalid result", Arrays.equals(buf, bufResult));
	}
