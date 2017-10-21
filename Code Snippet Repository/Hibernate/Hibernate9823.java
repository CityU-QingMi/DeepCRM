	private static void dump(Document document) {
		if ( !log.isTraceEnabled() ) {
			return;
		}

		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final Writer w = new PrintWriter( baos );

		try {
			final XMLWriter xw = new XMLWriter( w, new OutputFormat( " ", true ) );
			xw.write( document );
			w.flush();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}

		log.tracef( "Envers-generate entity mapping -----------------------------\n%s", baos.toString() );
		log.trace( "------------------------------------------------------------" );
	}
