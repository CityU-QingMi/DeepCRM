	private void writeFetch(Fetch fetch, int depth, PrintWriter printWriter) {
		printWriter.print( TreePrinterHelper.INSTANCE.generateNodePrefix( depth ) );

		if ( EntityFetch.class.isInstance( fetch ) ) {
			final EntityFetch entityFetch = (EntityFetch) fetch;
			printWriter.println( extractDetails( entityFetch ) );
			writeEntityReferenceFetches( entityFetch, depth+1, printWriter );
		}
		else if ( CompositeFetch.class.isInstance( fetch ) ) {
			final CompositeFetch compositeFetch = (CompositeFetch) fetch;
			printWriter.println( extractDetails( compositeFetch ) );
			writeCompositeFetchFetches( compositeFetch, depth+1, printWriter );
		}
		else if ( CollectionAttributeFetch.class.isInstance( fetch ) ) {
			final CollectionAttributeFetch collectionFetch = (CollectionAttributeFetch) fetch;
			printWriter.println( extractDetails( collectionFetch ) );
			writeCollectionReferenceFetches( collectionFetch, depth+1, printWriter );
		}
	}
