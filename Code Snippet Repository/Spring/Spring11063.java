	@Test
	public void noEncoding() {
		// Check no encoding of expressions or constraints
		PathPattern pp = parse("/{var:f o}");
		assertEquals("Separator(/) CaptureVariable({var:f o})",pp.toChainString());

		pp = parse("/{var:f o}_");
		assertEquals("Separator(/) Regex({var:f o}_)",pp.toChainString());
		
		pp = parse("{foo:f o}_ _{bar:b\\|o}");
		assertEquals("Regex({foo:f o}_ _{bar:b\\|o})",pp.toChainString());
	}
