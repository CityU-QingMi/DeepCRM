		private void assertFooPart(Part part) {
			assertEquals("fooPart", part.name());
			assertTrue(part instanceof FilePart);
			assertEquals("foo.txt", ((FilePart) part).filename());
			DataBuffer buffer = part
					.content()
					.reduce(DataBuffer::write)
					.block();
			assertEquals(12, buffer.readableByteCount());
			byte[] byteContent = new byte[12];
			buffer.read(byteContent);
			assertEquals("Lorem Ipsum.", new String(byteContent));
		}
