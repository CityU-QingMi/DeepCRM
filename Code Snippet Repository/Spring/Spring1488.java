	@Test
	public void arrayConvertedToIndexedBeanReference() {
		this.processor.setResources(new ByteArrayResource("foo: bar\nbar: [1,2,3]".getBytes()));
		this.processor.process(new MatchCallback() {
			@Override
			public void process(Properties properties, Map<String, Object> map) {
				assertEquals(4, properties.size());
				assertEquals("bar", properties.get("foo"));
				assertEquals("bar", properties.getProperty("foo"));
				assertEquals(1, properties.get("bar[0]"));
				assertEquals("1", properties.getProperty("bar[0]"));
				assertEquals(2, properties.get("bar[1]"));
				assertEquals("2", properties.getProperty("bar[1]"));
				assertEquals(3, properties.get("bar[2]"));
				assertEquals("3", properties.getProperty("bar[2]"));
			}
		});
	}
