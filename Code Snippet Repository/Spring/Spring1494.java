	@Test
	public void integerDeepKeyBehaves() {
		this.processor.setResources(new ByteArrayResource("foo:\n  1: bar".getBytes()));
		this.processor.process(new MatchCallback() {
			@Override
			public void process(Properties properties, Map<String, Object> map) {
				assertEquals("bar", properties.get("foo[1]"));
				assertEquals(1, properties.size());
			}
		});
	}
