	public void dumpResolutions(LoadPlan loadPlan) {
		if ( log.isDebugEnabled() ) {
			final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			final PrintStream printStream = new PrintStream( byteArrayOutputStream );
			final PrintWriter printWriter = new PrintWriter( printStream );

			printWriter.println( "LoadPlan QuerySpace resolutions" );

			for ( QuerySpace querySpace : loadPlan.getQuerySpaces().getRootQuerySpaces() ) {
				dumpQuerySpace( querySpace, 1, printWriter );
			}

			printWriter.flush();
			printStream.flush();

			log.debug( new String( byteArrayOutputStream.toByteArray() ) );
		}
	}
