	@Test
	public void testBadDocumentStart() throws Exception {
		this.processor.setResources(new ByteArrayResource("foo # a document\nbar: baz".getBytes()));
		this.exception.expect(ParserException.class);
		this.exception.expectMessage("line 2, column 1");
		this.processor.process(new MatchCallback() {
			@Override
			public void process(Properties properties, Map<String, Object> map) {
			}
		});
	}
