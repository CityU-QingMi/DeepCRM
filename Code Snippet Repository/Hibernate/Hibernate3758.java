	private void logTree(
			LoadPlan loadPlan,
			AliasResolutionContext aliasResolutionContext,
			PrintWriter printWriter) {
		printWriter.println( "LoadPlan(" + extractDetails( loadPlan ) + ")" );
		printWriter.println( TreePrinterHelper.INSTANCE.generateNodePrefix( 1 ) + "Returns" );
		for ( Return rtn : loadPlan.getReturns() ) {
			ReturnGraphTreePrinter.INSTANCE.write( rtn, 2, printWriter );
			printWriter.flush();
		}

		QuerySpaceTreePrinter.INSTANCE.write( loadPlan.getQuerySpaces(), 1, aliasResolutionContext, printWriter );

		printWriter.flush();
	}
