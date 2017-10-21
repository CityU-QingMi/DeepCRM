	@Test
	public void integerKeyBehaves() {
		this.processor.setResources(new ByteArrayResource("foo: bar\n1: bar".getBytes()));
		this.processor.process(new MatchCallback() {
			@Override
			public void process(Properties properties, Map<String, Object> map) {
				assertEquals("bar", properties.get("[1]"));
				assertEquals(2, properties.size());
			}
		});
	}
