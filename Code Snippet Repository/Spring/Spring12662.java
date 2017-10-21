	@Test
	public void addStatusController() {
		this.registry.addStatusController("/path", HttpStatus.NOT_FOUND);
		ParameterizableViewController controller = getController("/path");

		assertNull(controller.getViewName());
		assertEquals(HttpStatus.NOT_FOUND, controller.getStatusCode());
		assertTrue(controller.isStatusOnly());
		assertNotNull(controller.getApplicationContext());
	}
