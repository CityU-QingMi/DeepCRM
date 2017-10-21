	@Test
	public void compareTo() {
		FlashMap flashMap1 = new FlashMap();
		FlashMap flashMap2 = new FlashMap();
		assertEquals(0, flashMap1.compareTo(flashMap2));

		flashMap1.setTargetRequestPath("/path1");
		assertEquals(-1, flashMap1.compareTo(flashMap2));
		assertEquals(1, flashMap2.compareTo(flashMap1));

		flashMap2.setTargetRequestPath("/path2");
		assertEquals(0, flashMap1.compareTo(flashMap2));

		flashMap1.addTargetRequestParam("id", "1");
		assertEquals(-1, flashMap1.compareTo(flashMap2));
		assertEquals(1, flashMap2.compareTo(flashMap1));

		flashMap2.addTargetRequestParam("id", "2");
		assertEquals(0, flashMap1.compareTo(flashMap2));
	}
