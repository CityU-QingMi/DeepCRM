	@Test
	public void handleNavigationWithDecoration() {
		TestDecoratingNavigationHandler targetHandler = new TestDecoratingNavigationHandler();
		beanFactory.addBean("jsfNavigationHandler", targetHandler);

		delNavHandler.handleNavigation(facesContext, "fromAction", "myViewId");
		assertEquals("fromAction", targetHandler.lastFromAction);
		assertEquals("myViewId", targetHandler.lastOutcome);

		// Original handler must have been invoked as well...
		assertEquals("fromAction", origNavHandler.lastFromAction);
		assertEquals("myViewId", origNavHandler.lastOutcome);
	}
