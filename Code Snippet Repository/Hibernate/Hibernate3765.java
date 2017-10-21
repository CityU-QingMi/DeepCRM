	private void writeEntityReferenceFetches(EntityReference entityReference, int depth, PrintWriter printWriter) {
		if ( BidirectionalEntityReference.class.isInstance( entityReference ) ) {
			return;
		}
		if ( entityReference.getIdentifierDescription().hasFetches() ) {
			printWriter.println( TreePrinterHelper.INSTANCE.generateNodePrefix( depth ) + "(entity id) " );
			writeFetches( ( (FetchSource) entityReference.getIdentifierDescription() ).getFetches(), depth+1, printWriter );
		}

		writeFetches( entityReference.getFetches(), depth, printWriter );
	}
