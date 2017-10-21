	@Test
	public void defaultCommitOnAnnotatedClass() throws Throwable {
		final Exception ex = new Exception();
		try {
			testRollback(() -> annotationOnlyOnClassWithNoInterface.echo(ex), false);
			fail("Should have thrown Exception");
		}
		catch (Exception ex2) {
			assertSame(ex, ex2);
		}
	}
