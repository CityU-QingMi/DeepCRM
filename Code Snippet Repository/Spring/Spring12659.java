	@Test
	public void addViewControllerWithDefaultViewName() {
		this.registry.addViewController("/path");
		ParameterizableViewController controller = getController("/path");

		assertNull(controller.getViewName());
		assertNull(controller.getStatusCode());
		assertFalse(controller.isStatusOnly());
		assertNotNull(controller.getApplicationContext());
	}
