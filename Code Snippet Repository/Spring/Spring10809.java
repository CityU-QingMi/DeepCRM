	@Test
	public void beforeAndAfterPhaseWithMultipleTargets() {
		TestListener target1 = new TestListener();
		TestListener target2 = new TestListener();
		beanFactory.addBean("testListener1", target1);
		beanFactory.addBean("testListener2", target2);

		assertEquals(delPhaseListener.getPhaseId(), PhaseId.ANY_PHASE);
		PhaseEvent event = new PhaseEvent(facesContext, PhaseId.INVOKE_APPLICATION, new MockLifecycle());

		delPhaseListener.beforePhase(event);
		assertTrue(target1.beforeCalled);
		assertTrue(target2.beforeCalled);

		delPhaseListener.afterPhase(event);
		assertTrue(target1.afterCalled);
		assertTrue(target2.afterCalled);
	}
