	@Test
	public void addViewController() {
		this.registry.addViewController("/path").setViewName("viewName");
		ParameterizableViewController controller = getController("/path");

		assertEquals("viewName", controller.getViewName());
		assertNull(controller.getStatusCode());
		assertFalse(controller.isStatusOnly());
		assertNotNull(controller.getApplicationContext());
	}
