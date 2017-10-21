	@Test
	public void testServiceIsAdvised() {
		ClassPathXmlApplicationContext ctx =
			new ClassPathXmlApplicationContext(getClass().getSimpleName() + ".xml", getClass());

		service = (Service) ctx.getBean("service");

		try {
			this.service.serveMe();
			fail("service operation has not been advised by transaction interceptor");
		}
		catch (RuntimeException ex) {
			assertEquals("advice invoked",ex.getMessage());
		}
	}
