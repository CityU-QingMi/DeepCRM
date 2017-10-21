	private void writeCollectionReferenceFetches(
			CollectionReference collectionReference,
			int depth,
			PrintWriter printWriter) {
		final CollectionFetchableIndex indexGraph = collectionReference.getIndexGraph();
		if ( indexGraph != null ) {
			printWriter.print( TreePrinterHelper.INSTANCE.generateNodePrefix( depth ) + "(collection index) " );

			if ( EntityReference.class.isInstance( indexGraph ) ) {
				final EntityReference indexGraphAsEntityReference = (EntityReference) indexGraph;
				printWriter.println( extractDetails( indexGraphAsEntityReference ) );
				writeEntityReferenceFetches( indexGraphAsEntityReference, depth+1, printWriter );
			}
			else if ( CompositeFetch.class.isInstance( indexGraph ) ) {
				final CompositeFetch indexGraphAsCompositeFetch = (CompositeFetch) indexGraph;
				printWriter.println( extractDetails( indexGraphAsCompositeFetch ) );
				writeCompositeFetchFetches( indexGraphAsCompositeFetch, depth+1, printWriter );
			}
		}

		final CollectionFetchableElement elementGraph = collectionReference.getElementGraph();
		if ( elementGraph != null ) {
			printWriter.print( TreePrinterHelper.INSTANCE.generateNodePrefix( depth ) + "(collection element) " );

			if ( EntityReference.class.isInstance( elementGraph ) ) {
				final EntityReference elementGraphAsEntityReference = (EntityReference) elementGraph;
				printWriter.println( extractDetails( elementGraphAsEntityReference ) );
				writeEntityReferenceFetches( elementGraphAsEntityReference, depth+1, printWriter );
			}
			else if ( CompositeFetch.class.isInstance( elementGraph ) ) {
				final CompositeFetch elementGraphAsCompositeFetch = (CompositeFetch) elementGraph;
				printWriter.println( extractDetails( elementGraphAsCompositeFetch ) );
				writeCompositeFetchFetches( elementGraphAsCompositeFetch, depth+1, printWriter );
			}
		}
	}
