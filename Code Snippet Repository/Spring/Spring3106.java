	@Test
	public void mutualGetPutExclusion() {
		String key = "1";

		Long first = this.service.getOrPut(key, true);
		Long second = this.service.getOrPut(key, true);
		assertSame(first, second);

		// This forces the method to be executed again
		Long expected = first + 1;
		Long third = this.service.getOrPut(key, false);
		assertEquals(expected, third);

		Long fourth = this.service.getOrPut(key, true);
		assertSame(third, fourth);
	}
