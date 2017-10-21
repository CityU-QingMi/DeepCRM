	@Test
	@SuppressWarnings("")
	public void unmarshalAnXmlReferingToAWrappedXmlElementDecl() throws Exception {
		// SPR-10714
		unmarshaller = new Jaxb2Marshaller();
		unmarshaller.setPackagesToScan(new String[] { "org.springframework.oxm.jaxb" });
		unmarshaller.afterPropertiesSet();
		Source source = new StreamSource(new StringReader(
				"<brand-airplane><name>test</name></brand-airplane>"));
		JAXBElement<Airplane> airplane = (JAXBElement<Airplane>) unmarshaller.unmarshal(source);
		assertEquals("Unmarshalling via explicit @XmlRegistry tag should return correct type",
				"test", airplane.getValue().getName());
	}
