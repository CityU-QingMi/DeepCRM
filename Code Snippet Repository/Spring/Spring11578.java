	@Test
	public void transformManifest() throws Exception {
		MockServerHttpRequest request = MockServerHttpRequest.get("/static/test.appcache").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		VersionResourceResolver versionResolver = new VersionResourceResolver();
		versionResolver.setStrategyMap(Collections.singletonMap("/**", new ContentVersionStrategy()));

		PathResourceResolver pathResolver = new PathResourceResolver();
		pathResolver.setAllowedLocations(new ClassPathResource("test/", getClass()));

		List<ResourceResolver> resolvers = Arrays.asList(versionResolver, pathResolver);
		ResourceResolverChain resolverChain = new DefaultResourceResolverChain(resolvers);

		List<ResourceTransformer> transformers = new ArrayList<>();
		transformers.add(new CssLinkResourceTransformer());
		this.chain = new DefaultResourceTransformerChain(resolverChain, transformers);

		Resource resource = new ClassPathResource("test/test.appcache", getClass());
		Resource result = this.transformer.transform(exchange, resource, this.chain).block(Duration.ofMillis(5000));
		byte[] bytes = FileCopyUtils.copyToByteArray(result.getInputStream());
		String content = new String(bytes, "UTF-8");

		assertThat("should rewrite resource links", content,
				Matchers.containsString("/static/foo-e36d2e05253c6c7085a91522ce43a0b4.css"));
		assertThat("should rewrite resource links", content,
				Matchers.containsString("/static/bar-11e16cf79faee7ac698c805cf28248d2.css"));
		assertThat("should rewrite resource links", content,
				Matchers.containsString("/static/js/bar-bd508c62235b832d960298ca6c0b7645.js"));

		assertThat("should not rewrite external resources", content,
				Matchers.containsString("//example.org/style.css"));
		assertThat("should not rewrite external resources", content,
				Matchers.containsString("http://example.org/image.png"));

		assertThat("should generate fingerprint", content,
				Matchers.containsString("# Hash: 8eefc904df3bd46537fa7bdbbc5ab9fb"));
	}
