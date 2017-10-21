	@Test
	public void statusRanges() throws Exception {
		for (HttpStatus status : HttpStatus.values()) {
			MockHttpServletResponse response = new MockHttpServletResponse();
			response.setStatus(status.value());
			MvcResult mvcResult = new StubMvcResult(request, null, null, null, null, null, response);
			switch (status.series().value()) {
				case 1:
					this.matchers.is1xxInformational().match(mvcResult);
					break;
				case 2:
					this.matchers.is2xxSuccessful().match(mvcResult);
					break;
				case 3:
					this.matchers.is3xxRedirection().match(mvcResult);
					break;
				case 4:
					this.matchers.is4xxClientError().match(mvcResult);
					break;
				case 5:
					this.matchers.is5xxServerError().match(mvcResult);
					break;
				default:
					fail("Unexpected range for status code value " + status);
			}
		}
	}
