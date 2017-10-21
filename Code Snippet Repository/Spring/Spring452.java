	@Test
	public void defaultCommitOnSubclassOfAnnotatedClass() throws Throwable {
		final Exception ex = new Exception();
		try {
			testRollback(() -> new SubclassOfClassWithTransactionalAnnotation().echo(ex), false);
			fail("Should have thrown Exception");
		}
		catch (Exception ex2) {
			assertSame(ex, ex2);
		}
	}
