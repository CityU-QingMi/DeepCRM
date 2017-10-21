	@Test
	public void testPrint() throws Exception {
		StringWriter writer = new StringWriter();

		standaloneSetup(new SimpleController())
			.build()
			.perform(get("/").content("Hello Request".getBytes()))
			.andDo(log())
			.andDo(print())
			.andDo(print(System.err))
			.andDo(print(writer))
		;

		System.out.println();
		System.out.println("===============================================================");
		System.out.println(writer.toString());
	}
