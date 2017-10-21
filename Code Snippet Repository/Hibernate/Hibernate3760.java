	public void write(
			QuerySpaces spaces,
			int depth,
			AliasResolutionContext aliasResolutionContext,
			PrintWriter printWriter) {
		if ( spaces == null ) {
			printWriter.println( "QuerySpaces is null!" );
			return;
		}

		printWriter.println( TreePrinterHelper.INSTANCE.generateNodePrefix( depth ) + "QuerySpaces" );

		for ( QuerySpace querySpace : spaces.getRootQuerySpaces() ) {
			writeQuerySpace( querySpace, depth + 1, aliasResolutionContext, printWriter );
		}

		printWriter.flush();
	}
