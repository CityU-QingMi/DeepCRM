	@Test
	public void httpSession() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new TestController())
				.apply(sharedHttpSession())
				.build();

		String url = "/session";

		MvcResult result = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		HttpSession session = result.getRequest().getSession(false);
		assertNotNull(session);
		assertEquals(1, session.getAttribute("counter"));

		result = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		session = result.getRequest().getSession(false);
		assertNotNull(session);
		assertEquals(2, session.getAttribute("counter"));

		result = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		session = result.getRequest().getSession(false);
		assertNotNull(session);
		assertEquals(3, session.getAttribute("counter"));
	}
