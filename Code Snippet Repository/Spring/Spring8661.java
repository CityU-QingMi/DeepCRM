	@Test
	public void sessionAttributesAreClearedBetweenInvocations() throws Exception {

		this.mvc.perform(get("/"))
			.andExpect(content().string(HELLO))
			.andExpect(request().sessionAttribute(FOO, nullValue()));

		this.mvc.perform(get("/").sessionAttr(FOO, BAR))
			.andExpect(content().string(HELLO))
			.andExpect(request().sessionAttribute(FOO, BAR));

		this.mvc.perform(get("/"))
			.andExpect(content().string(HELLO))
			.andExpect(request().sessionAttribute(FOO, nullValue()));
	}
