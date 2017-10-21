	private void writeJoins(
			Iterable<Join> joins,
			int depth,
			AliasResolutionContext aliasResolutionContext,
			PrintWriter printWriter) {
		for ( Join join : joins ) {
			printWriter.println(
					TreePrinterHelper.INSTANCE.generateNodePrefix( depth ) + extractDetails( join )
			);
			writeQuerySpace( join.getRightHandSide(), depth+1, aliasResolutionContext, printWriter );
		}
	}
