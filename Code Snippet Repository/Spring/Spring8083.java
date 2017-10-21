	@Test
	public void unmarshalStreamSourceWithXmlOptions() throws Exception {
		final javax.xml.bind.Unmarshaller unmarshaller = mock(javax.xml.bind.Unmarshaller.class);
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller() {
			@Override
			protected javax.xml.bind.Unmarshaller createUnmarshaller() {
				return unmarshaller;
			}
		};

		// 1. external-general-entities and dtd support disabled (default)

		marshaller.unmarshal(new StreamSource("1"));
		ArgumentCaptor<SAXSource> sourceCaptor = ArgumentCaptor.forClass(SAXSource.class);
		verify(unmarshaller).unmarshal(sourceCaptor.capture());

		SAXSource result = sourceCaptor.getValue();
		assertEquals(true, result.getXMLReader().getFeature("http://apache.org/xml/features/disallow-doctype-decl"));
		assertEquals(false, result.getXMLReader().getFeature("http://xml.org/sax/features/external-general-entities"));

		// 2. external-general-entities and dtd support enabled

		reset(unmarshaller);
		marshaller.setProcessExternalEntities(true);
		marshaller.setSupportDtd(true);

		marshaller.unmarshal(new StreamSource("1"));
		verify(unmarshaller).unmarshal(sourceCaptor.capture());

		result = sourceCaptor.getValue();
		assertEquals(false, result.getXMLReader().getFeature("http://apache.org/xml/features/disallow-doctype-decl"));
		assertEquals(true, result.getXMLReader().getFeature("http://xml.org/sax/features/external-general-entities"));
	}
