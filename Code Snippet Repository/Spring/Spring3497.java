	@Test
	public void testResourceArrayPropertyEditor() throws IOException {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(CONTEXT_WILDCARD);
		Service service = (Service) ctx.getBean("service");
		assertEquals(3, service.getResources().length);
		List<Resource> resources = Arrays.asList(service.getResources());
		assertTrue(resources.contains(new FileSystemResource(new ClassPathResource(FQ_CONTEXT_A).getFile())));
		assertTrue(resources.contains(new FileSystemResource(new ClassPathResource(FQ_CONTEXT_B).getFile())));
		assertTrue(resources.contains(new FileSystemResource(new ClassPathResource(FQ_CONTEXT_C).getFile())));
		ctx.close();
	}
