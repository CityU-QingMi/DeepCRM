	@Test
	public void writeSingleRegion() throws Exception {

		testWrite(get("/").range(of(0, 5)).build());

		assertThat(this.response.getHeaders().getContentType(), is(TEXT_PLAIN));
		assertThat(this.response.getHeaders().getFirst(HttpHeaders.CONTENT_RANGE), is("bytes 0-5/39"));
		assertThat(this.response.getHeaders().getContentLength(), is(6L));

		StepVerifier.create(this.response.getBodyAsString()).expectNext("Spring").expectComplete().verify();
	}
