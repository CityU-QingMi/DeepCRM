	@Test
	public void beforeAndAfterPhaseWithSingleTarget() {
		TestListener target = new TestListener();
		beanFactory.addBean("testListener", target);

		assertEquals(delPhaseListener.getPhaseId(), PhaseId.ANY_PHASE);
		PhaseEvent event = new PhaseEvent(facesContext, PhaseId.INVOKE_APPLICATION, new MockLifecycle());

		delPhaseListener.beforePhase(event);
		assertTrue(target.beforeCalled);

		delPhaseListener.afterPhase(event);
		assertTrue(target.afterCalled);
	}
