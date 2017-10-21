	@Test
	public void testBadResource() throws Exception {
		this.processor.setResources(new ByteArrayResource("foo: bar\ncd\nspam:\n  foo: baz".getBytes()));
		this.exception.expect(ScannerException.class);
		this.exception.expectMessage("line 3, column 1");
		this.processor.process(new MatchCallback() {
			@Override
			public void process(Properties properties, Map<String, Object> map) {
			}
		});
	}
