	@Test
	public void testLoadArrayOfObject() throws Exception {
		YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
		factory.setResources(new ByteArrayResource(
				"foo:\n- bar:\n    spam: crap\n- baz\n- one: two\n  three: four".getBytes()
		));
		Properties properties = factory.getObject();
		assertThat(properties.getProperty("foo[0].bar.spam"), equalTo("crap"));
		assertThat(properties.getProperty("foo[1]"), equalTo("baz"));
		assertThat(properties.getProperty("foo[2].one"), equalTo("two"));
		assertThat(properties.getProperty("foo[2].three"), equalTo("four"));
		assertThat(properties.get("foo"), is(nullValue()));
	}
