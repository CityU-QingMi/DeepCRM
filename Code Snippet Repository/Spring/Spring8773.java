	@Test
	public void longValueWithMissingResponseHeader() throws Exception {
		try {
			this.mockMvc.perform(get("/persons/1").header(IF_MODIFIED_SINCE, now))
					.andExpect(status().isNotModified())
					.andExpect(header().longValue("X-Custom-Header", 99L));

			fail(ERROR_MESSAGE);
		}
		catch (AssertionError err) {
			if (ERROR_MESSAGE.equals(err.getMessage())) {
				throw err;
			}
			assertEquals("Response does not contain header 'X-Custom-Header'", err.getMessage());
		}
	}
