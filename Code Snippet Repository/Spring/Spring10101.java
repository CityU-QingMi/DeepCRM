	@Test
	public void resolveParts() throws IOException {
		ServerHttpRequest request = generateMultipartRequest();
		ResolvableType elementType = forClassWithGenerics(MultiValueMap.class, String.class, Part.class);
		MultiValueMap<String, Part> parts = this.reader.readMono(elementType, request, emptyMap()).block();
		assertEquals(2, parts.size());

		assertTrue(parts.containsKey("fooPart"));
		Part part = parts.getFirst("fooPart");
		assertTrue(part instanceof FilePart);
		assertEquals("fooPart", part.name());
		assertEquals("foo.txt", ((FilePart) part).filename());
		DataBuffer buffer = part.content().reduce(DataBuffer::write).block();
		assertEquals(12, buffer.readableByteCount());
		byte[] byteContent = new byte[12];
		buffer.read(byteContent);
		assertEquals("Lorem Ipsum.", new String(byteContent));

		assertTrue(parts.containsKey("barPart"));
		part = parts.getFirst("barPart");
		assertTrue(part instanceof FormFieldPart);
		assertEquals("barPart", part.name());
		assertEquals("bar", ((FormFieldPart) part).value());
	}
