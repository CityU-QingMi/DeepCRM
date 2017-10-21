	@Test
	public void testConcatenateStringArrays() {
		String[] input1 = new String[] {"myString2"};
		String[] input2 = new String[] {"myString1", "myString2"};
		String[] result = StringUtils.concatenateStringArrays(input1, input2);
		assertEquals(3, result.length);
		assertEquals("myString2", result[0]);
		assertEquals("myString1", result[1]);
		assertEquals("myString2", result[2]);

		assertArrayEquals(input1, StringUtils.concatenateStringArrays(input1, null));
		assertArrayEquals(input2, StringUtils.concatenateStringArrays(null, input2));
		assertNull(StringUtils.concatenateStringArrays(null, null));
	}
