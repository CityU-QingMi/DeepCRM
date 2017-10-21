	@Test
	public void testMergeStringArrays() {
		String[] input1 = new String[] {"myString2"};
		String[] input2 = new String[] {"myString1", "myString2"};
		String[] result = StringUtils.mergeStringArrays(input1, input2);
		assertEquals(2, result.length);
		assertEquals("myString2", result[0]);
		assertEquals("myString1", result[1]);

		assertArrayEquals(input1, StringUtils.mergeStringArrays(input1, null));
		assertArrayEquals(input2, StringUtils.mergeStringArrays(null, input2));
		assertNull(StringUtils.mergeStringArrays(null, null));
	}
