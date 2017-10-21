	@Test
	public void nonMatchingRollbackOnApplied() throws Throwable {
		assertEquals(0, this.txManager.begun);
		IOException test = new IOException();
		try {
			new JtaAnnotationPublicAnnotatedMember().echo(test);
			fail("Should have thrown an exception");
		}
		catch (Throwable throwable) {
			assertEquals("wrong exception", test, throwable);
		}
		assertEquals(1, this.txManager.commits);
		assertEquals(0, this.txManager.rollbacks);
	}
