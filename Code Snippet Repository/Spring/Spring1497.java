	@Test
	public void testLoadResourceWithSelectedDocuments() throws Exception {
		YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
		factory.setResources(new ByteArrayResource(
				"foo: bar\nspam: baz\n---\nfoo: bag\nspam: bad".getBytes()));
		factory.setDocumentMatchers(new DocumentMatcher() {
			@Override
			public MatchStatus matches(Properties properties) {
				return ("bag".equals(properties.getProperty("foo")) ?
						MatchStatus.FOUND : MatchStatus.NOT_FOUND);
			}
		});
		Properties properties = factory.getObject();
		assertThat(properties.getProperty("foo"), equalTo("bag"));
		assertThat(properties.getProperty("spam"), equalTo("bad"));
	}
