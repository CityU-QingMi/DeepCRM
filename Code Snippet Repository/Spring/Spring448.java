	@Test
	public void matchingRollbackOnApplied() throws Throwable {
		assertEquals(0, this.txManager.begun);
		InterruptedException test = new InterruptedException();
		try {
			new JtaAnnotationPublicAnnotatedMember().echo(test);
			fail("Should have thrown an exception");
		}
		catch (Throwable throwable) {
			assertEquals("wrong exception", test, throwable);
		}
		assertEquals(1, this.txManager.rollbacks);
		assertEquals(0, this.txManager.commits);
	}
