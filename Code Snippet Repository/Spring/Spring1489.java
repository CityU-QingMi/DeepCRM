	@Test
	public void testStringResource() throws Exception {
		this.processor.setResources(new ByteArrayResource("foo # a document that is a literal".getBytes()));
		this.processor.process(new MatchCallback() {
			@Override
			public void process(Properties properties, Map<String, Object> map) {
				assertEquals("foo", map.get("document"));
			}
		});
	}
