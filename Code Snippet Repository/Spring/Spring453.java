	@Test
	public void defaultCommitOnSubclassOfClassWithTransactionalMethodAnnotated() throws Throwable {
		final Exception ex = new Exception();
		try {
			testRollback(() -> new SubclassOfClassWithTransactionalMethodAnnotation().echo(ex), false);
			fail("Should have thrown Exception");
		}
		catch (Exception ex2) {
			assertSame(ex, ex2);
		}
	}
