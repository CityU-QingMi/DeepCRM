	@Test
	public void defaultRollbackOnAnnotatedClass() throws Throwable {
		final RuntimeException ex = new RuntimeException();
		try {
			testRollback(() -> annotationOnlyOnClassWithNoInterface.echo(ex), true);
			fail("Should have thrown RuntimeException");
		}
		catch (RuntimeException ex2) {
			assertSame(ex, ex2);
		}
	}
