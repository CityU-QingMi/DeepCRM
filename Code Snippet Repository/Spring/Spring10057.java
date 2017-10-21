	@Test
	public void writeResource() throws Exception {

		testWrite(get("/").build());

		assertThat(this.response.getHeaders().getContentType(), is(TEXT_PLAIN));
		assertThat(this.response.getHeaders().getContentLength(), is(39L));
		assertThat(this.response.getHeaders().getFirst(HttpHeaders.ACCEPT_RANGES), is("bytes"));

		String content = "Spring Framework test resource content.";
		StepVerifier.create(this.response.getBodyAsString()).expectNext(content).expectComplete().verify();
	}
