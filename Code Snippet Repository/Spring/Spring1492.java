	@Test
	public void mapConvertedToIndexedBeanReference() {
		this.processor.setResources(new ByteArrayResource("foo: bar\nbar:\n spam: bucket".getBytes()));
		this.processor.process(new MatchCallback() {
			@Override
			public void process(Properties properties, Map<String, Object> map) {
				// System.err.println(properties);
				assertEquals("bucket", properties.get("bar.spam"));
				assertEquals(2, properties.size());
			}
		});
	}
