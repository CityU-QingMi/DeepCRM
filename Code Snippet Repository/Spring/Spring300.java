	@Test
	public void testAfterAdviceTypes() throws Exception {
		Echo target = new Echo();

		ExceptionHandling afterReturningAspect = new ExceptionHandling();
		List<Advisor> advisors = getFixture().getAdvisors(new SingletonMetadataAwareAspectInstanceFactory(afterReturningAspect,"someBean"));
		Echo echo = (Echo) createProxy(target,
				advisors,
				Echo.class);
		assertEquals(0, afterReturningAspect.successCount);
		assertEquals("", echo.echo(""));
		assertEquals(1, afterReturningAspect.successCount);
		assertEquals(0, afterReturningAspect.failureCount);
		try {
			echo.echo(new FileNotFoundException());
			fail();
		}
		catch (FileNotFoundException ex) {
			// Ok
		}
		catch (Exception ex) {
			fail();
		}
		assertEquals(1, afterReturningAspect.successCount);
		assertEquals(1, afterReturningAspect.failureCount);
		assertEquals(afterReturningAspect.failureCount + afterReturningAspect.successCount, afterReturningAspect.afterCount);
	}
