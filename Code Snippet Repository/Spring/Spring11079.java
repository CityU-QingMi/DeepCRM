	@Test
	public void parameters() {
		// CaptureVariablePathElement
		PathPattern.PathMatchInfo result = matchAndExtract("/abc/{var}","/abc/one;two=three;four=five");
		assertEquals("one",result.getUriVariables().get("var"));
		assertEquals("three",result.getMatrixVariables().get("var").getFirst("two"));
		assertEquals("five",result.getMatrixVariables().get("var").getFirst("four"));
		// RegexPathElement
		result = matchAndExtract("/abc/{var1}_{var2}","/abc/123_456;a=b;c=d");
		assertEquals("123",result.getUriVariables().get("var1"));
		assertEquals("456",result.getUriVariables().get("var2"));
		// vars associated with second variable
		assertNull(result.getMatrixVariables().get("var1"));
		assertNull(result.getMatrixVariables().get("var1"));
		assertEquals("b",result.getMatrixVariables().get("var2").getFirst("a"));
		assertEquals("d",result.getMatrixVariables().get("var2").getFirst("c"));
		// CaptureTheRestPathElement
		result = matchAndExtract("/{*var}","/abc/123_456;a=b;c=d");
		assertEquals("/abc/123_456",result.getUriVariables().get("var"));
		assertEquals("b",result.getMatrixVariables().get("var").getFirst("a"));
		assertEquals("d",result.getMatrixVariables().get("var").getFirst("c"));
		result = matchAndExtract("/{*var}","/abc/123_456;a=b;c=d/789;a=e;f=g");
		assertEquals("/abc/123_456/789",result.getUriVariables().get("var"));
		assertEquals("[b, e]",result.getMatrixVariables().get("var").get("a").toString());
		assertEquals("d",result.getMatrixVariables().get("var").getFirst("c"));
		assertEquals("g",result.getMatrixVariables().get("var").getFirst("f"));

		result = matchAndExtract("/abc/{var}","/abc/one");
		assertEquals("one",result.getUriVariables().get("var"));
		assertNull(result.getMatrixVariables().get("var"));
		
		result = matchAndExtract("","");
		assertNotNull(result);
		result = matchAndExtract("","/");
		assertNotNull(result);
	}
