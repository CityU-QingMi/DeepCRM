	@Test
	public void unmarshalStreamSourceWithXmlOptions() throws Exception {
		final AtomicReference<XMLReader> result = new AtomicReference<>();
		CastorMarshaller marshaller = new CastorMarshaller() {
			@Override
			protected Object unmarshalSaxReader(XMLReader xmlReader, InputSource inputSource) {
				result.set(xmlReader);
				return null;
			}
		};

		// 1. external-general-entities and dtd support disabled (default)
		marshaller.unmarshal(new StreamSource("1"));
		assertNotNull(result.get());
		assertEquals(true, result.get().getFeature("http://apache.org/xml/features/disallow-doctype-decl"));
		assertEquals(false, result.get().getFeature("http://xml.org/sax/features/external-general-entities"));

		// 2. external-general-entities and dtd support enabled
		result.set(null);
		marshaller.setSupportDtd(true);
		marshaller.setProcessExternalEntities(true);
		marshaller.unmarshal(new StreamSource("1"));
		assertNotNull(result.get());
		assertEquals(false, result.get().getFeature("http://apache.org/xml/features/disallow-doctype-decl"));
		assertEquals(true, result.get().getFeature("http://xml.org/sax/features/external-general-entities"));
	}
