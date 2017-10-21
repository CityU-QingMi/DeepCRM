	@Test
	public void testLoadResourcesWithOverride() throws Exception {
		YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
		factory.setResources(
				new ByteArrayResource("foo: bar\nspam:\n  foo: baz".getBytes()),
				new ByteArrayResource("foo:\n  bar: spam".getBytes()));
		Properties properties = factory.getObject();
		assertThat(properties.getProperty("foo"), equalTo("bar"));
		assertThat(properties.getProperty("spam.foo"), equalTo("baz"));
		assertThat(properties.getProperty("foo.bar"), equalTo("spam"));
	}
