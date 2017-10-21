	@Test
	public void includes() throws Exception {
		MimeType textPlain = MimeTypeUtils.TEXT_PLAIN;
		assertTrue("Equal types is not inclusive", textPlain.includes(textPlain));
		MimeType allText = new MimeType("text");

		assertTrue("All subtypes is not inclusive", allText.includes(textPlain));
		assertFalse("All subtypes is inclusive", textPlain.includes(allText));

		assertTrue("All types is not inclusive", MimeTypeUtils.ALL.includes(textPlain));
		assertFalse("All types is inclusive", textPlain.includes(MimeTypeUtils.ALL));

		assertTrue("All types is not inclusive", MimeTypeUtils.ALL.includes(textPlain));
		assertFalse("All types is inclusive", textPlain.includes(MimeTypeUtils.ALL));

		MimeType applicationSoapXml = new MimeType("application", "soap+xml");
		MimeType applicationWildcardXml = new MimeType("application", "*+xml");
		MimeType suffixXml = new MimeType("application", "x.y+z+xml"); // SPR-15795

		assertTrue(applicationSoapXml.includes(applicationSoapXml));
		assertTrue(applicationWildcardXml.includes(applicationWildcardXml));
		assertTrue(applicationWildcardXml.includes(suffixXml));

		assertTrue(applicationWildcardXml.includes(applicationSoapXml));
		assertFalse(applicationSoapXml.includes(applicationWildcardXml));
		assertFalse(suffixXml.includes(applicationWildcardXml));

		assertFalse(applicationWildcardXml.includes(MimeTypeUtils.APPLICATION_JSON));
	}
