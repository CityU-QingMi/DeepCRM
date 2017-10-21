	private String toString(LoadPlan loadPlan, AliasResolutionContext aliasResolutionContext) {
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		final PrintStream printStream = new PrintStream( byteArrayOutputStream );
		final PrintWriter printWriter = new PrintWriter( printStream );

		logTree( loadPlan, aliasResolutionContext, printWriter );

		printWriter.flush();
		printStream.flush();

		return new String( byteArrayOutputStream.toByteArray() );
	}
