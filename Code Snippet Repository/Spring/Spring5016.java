	@Test
	public void nestedCheckedExceptionWithRootCause() {
		String myMessage = "mesg for this exception";
		String rootCauseMesg = "this is the obscure message of the root cause";
		Exception rootCause = new Exception(rootCauseMesg);
		// Making a class abstract doesn't _really_ prevent instantiation :-)
		NestedCheckedException nex = new NestedCheckedException(myMessage, rootCause) {};
		assertEquals(nex.getCause(), rootCause);
		assertTrue(nex.getMessage().indexOf(myMessage) != -1);
		assertTrue(nex.getMessage().indexOf(rootCauseMesg) != -1);

		// check PrintStackTrace
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(baos);
		nex.printStackTrace(pw);
		pw.flush();
		String stackTrace = new String(baos.toByteArray());
		assertFalse(stackTrace.indexOf(rootCause.getClass().getName()) == -1);
		assertFalse(stackTrace.indexOf(rootCauseMesg) == -1);
	}
