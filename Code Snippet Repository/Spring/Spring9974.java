	@Test
	public void toResourceRegion() {
		byte[] bytes = "Spring Framework".getBytes(StandardCharsets.UTF_8);
		ByteArrayResource resource = new ByteArrayResource(bytes);
		HttpRange range = HttpRange.createByteRange(0, 5);
		ResourceRegion region = range.toResourceRegion(resource);
		assertEquals(resource, region.getResource());
		assertEquals(0L, region.getPosition());
		assertEquals(6L, region.getCount());
	}
