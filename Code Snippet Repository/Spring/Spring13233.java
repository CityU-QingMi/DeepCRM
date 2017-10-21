	@Test
	public void transformManifest() throws Exception {
		this.request = new MockHttpServletRequest("GET", "/static/test.appcache");
		Resource resource = new ClassPathResource("test/test.appcache", getClass());
		Resource result = this.transformer.transform(this.request, resource, this.chain);
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
				Matchers.containsString("# Hash: 4bf0338bcbeb0a5b3a4ec9ed8864107d"));
	}
