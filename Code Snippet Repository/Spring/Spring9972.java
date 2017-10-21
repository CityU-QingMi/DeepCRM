	@Test
	public void contentDisposition() {
		ContentDisposition disposition = headers.getContentDisposition();
		assertNotNull(disposition);
		assertEquals("Invalid Content-Disposition header", ContentDisposition.empty(), headers.getContentDisposition());

		disposition = ContentDisposition.builder("attachment").name("foo").filename("foo.txt").size(123L).build();
		headers.setContentDisposition(disposition);
		assertEquals("Invalid Content-Disposition header", disposition, headers.getContentDisposition());
	}
