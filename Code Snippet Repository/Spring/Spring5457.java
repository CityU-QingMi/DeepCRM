	@Test
	public void isCompatible() throws Exception {
		MimeType textPlain = MimeTypeUtils.TEXT_PLAIN;
		assertTrue("Equal types is not compatible", textPlain.isCompatibleWith(textPlain));
		MimeType allText = new MimeType("text");

		assertTrue("All subtypes is not compatible", allText.isCompatibleWith(textPlain));
		assertTrue("All subtypes is not compatible", textPlain.isCompatibleWith(allText));

		assertTrue("All types is not compatible", MimeTypeUtils.ALL.isCompatibleWith(textPlain));
		assertTrue("All types is not compatible", textPlain.isCompatibleWith(MimeTypeUtils.ALL));

		assertTrue("All types is not compatible", MimeTypeUtils.ALL.isCompatibleWith(textPlain));
		assertTrue("All types is compatible", textPlain.isCompatibleWith(MimeTypeUtils.ALL));

		MimeType applicationSoapXml = new MimeType("application", "soap+xml");
		MimeType applicationWildcardXml = new MimeType("application", "*+xml");
		MimeType suffixXml = new MimeType("application", "x.y+z+xml"); // SPR-15795

		assertTrue(applicationSoapXml.isCompatibleWith(applicationSoapXml));
		assertTrue(applicationWildcardXml.isCompatibleWith(applicationWildcardXml));
		assertTrue(applicationWildcardXml.isCompatibleWith(suffixXml));

		assertTrue(applicationWildcardXml.isCompatibleWith(applicationSoapXml));
		assertTrue(applicationSoapXml.isCompatibleWith(applicationWildcardXml));
		assertTrue(suffixXml.isCompatibleWith(applicationWildcardXml));

		assertFalse(applicationWildcardXml.isCompatibleWith(MimeTypeUtils.APPLICATION_JSON));
	}
