	@Test
	public void testPhases() {
		int phase1 = getPhase("listener1");
		int phase2 = getPhase("listener2");
		int phase3 = getPhase("listener3");
		int phase4 = getPhase("listener4");
		int defaultPhase = getPhase(DefaultMessageListenerContainer.class.getName() + "#0");
		assertEquals(99, phase1);
		assertEquals(99, phase2);
		assertEquals(77, phase3);
		assertEquals(77, phase4);
		assertEquals(Integer.MAX_VALUE, defaultPhase);
	}
