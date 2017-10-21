	@Test
	public void addObjectToSingleNonNullElementArray() {
		String existingElement = "foo";
		String[] array = new String[] {existingElement};
		String newElement = "bar";
		String[] newArray = ObjectUtils.addObjectToArray(array, newElement);
		assertEquals(2, newArray.length);
		assertEquals(existingElement, newArray[0]);
		assertEquals(newElement, newArray[1]);
	}
