	@Test
	public void basic() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		String actual;

		actual = restTemplate.getForObject(createUrl("/webApp1/test"), String.class);
		assertEquals("Tested in /webApp1", actual);

		actual = restTemplate.getForObject(createUrl("/webApp2/test"), String.class);
		assertEquals("Tested in /webApp2", actual);
	}
