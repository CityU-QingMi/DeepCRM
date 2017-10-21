	@Test
	public void fillInClientStackTraceIfPossibleSunnyDay() throws Exception {
		try {
			throw new IllegalStateException("Mmm");
		}
		catch (Exception ex) {
			int originalStackTraceLngth = ex.getStackTrace().length;
			RemoteInvocationUtils.fillInClientStackTraceIfPossible(ex);
			assertTrue("Stack trace not being filled in",
					ex.getStackTrace().length > originalStackTraceLngth);
		}
	}
