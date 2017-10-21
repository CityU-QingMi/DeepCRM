	@Test
	public void testSetArrayElementValueAllPrimitiveTypesErrors() {
		// none of these sets are possible due to (expected) conversion problems
		setValueExpectError("arrayContainer.ints[1]", "wibble");
		setValueExpectError("arrayContainer.floats[1]", "dribble");
		setValueExpectError("arrayContainer.booleans[1]", "nein");
		// TODO -- this fails with NPE due to ArrayToObject converter - discuss with Andy
		//setValueExpectError("arrayContainer.doubles[1]", new ArrayList<String>());
		//setValueExpectError("arrayContainer.shorts[1]", new ArrayList<String>());
		//setValueExpectError("arrayContainer.longs[1]", new ArrayList<String>());
		setValueExpectError("arrayContainer.bytes[1]", "NaB");
		setValueExpectError("arrayContainer.chars[1]", "NaC");
	}
