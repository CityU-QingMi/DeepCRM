	@Test
	public void testLoadResourceWithDefaultMatchSkippingMissedMatch() throws Exception {
		YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
		factory.setMatchDefault(true);
		factory.setResources(new ByteArrayResource(
				"one: two\n---\nfoo: bag\nspam: bad\n---\nfoo: bar\nspam: baz".getBytes()));
		factory.setDocumentMatchers(new DocumentMatcher() {
			@Override
			public MatchStatus matches(Properties properties) {
				if (!properties.containsKey("foo")) {
					return MatchStatus.ABSTAIN;
				}
				return ("bag".equals(properties.getProperty("foo")) ?
						MatchStatus.FOUND : MatchStatus.NOT_FOUND);
			}
		});
		Properties properties = factory.getObject();
		assertThat(properties.getProperty("foo"), equalTo("bag"));
		assertThat(properties.getProperty("spam"), equalTo("bad"));
		assertThat(properties.getProperty("one"), equalTo("two"));
	}
