	@Test
	@SuppressWarnings("")
	public void flattenedMapIsSameAsPropertiesButOrdered() {
		this.processor.setResources(new ByteArrayResource("foo: bar\nbar:\n spam: bucket".getBytes()));
		this.processor.process(new MatchCallback() {
			@Override
			public void process(Properties properties, Map<String, Object> map) {
				assertEquals("bucket", properties.get("bar.spam"));
				assertEquals(2, properties.size());
				Map<String, Object> flattenedMap = processor.getFlattenedMap(map);
				assertEquals("bucket", flattenedMap.get("bar.spam"));
				assertEquals(2, flattenedMap.size());
				assertTrue(flattenedMap instanceof LinkedHashMap);
				Map<String, Object> bar = (Map<String, Object>) map.get("bar");
				assertEquals("bucket", bar.get("spam"));
			}
		});
	}
