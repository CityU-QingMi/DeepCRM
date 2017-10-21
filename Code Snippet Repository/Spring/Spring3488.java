	@Test
	public void testMultipleConfigLocations() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				FQ_CONTEXT_B, FQ_CONTEXT_C, FQ_CONTEXT_A);
		assertTrue(ctx.containsBean("service"));
		assertTrue(ctx.containsBean("logicOne"));
		assertTrue(ctx.containsBean("logicTwo"));

		// re-refresh (after construction refresh)
		Service service = (Service) ctx.getBean("service");
		ctx.refresh();
		assertTrue(service.isProperlyDestroyed());

		// regular close call
		service = (Service) ctx.getBean("service");
		ctx.close();
		assertTrue(service.isProperlyDestroyed());

		// re-activating and re-closing the context (SPR-13425)
		ctx.refresh();
		service = (Service) ctx.getBean("service");
		ctx.close();
		assertTrue(service.isProperlyDestroyed());
	}
