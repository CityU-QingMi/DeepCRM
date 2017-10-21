	public void write(Return rootReturn, int depth, PrintWriter printWriter) {
		if ( rootReturn == null ) {
			printWriter.println( "Return is null!" );
			return;
		}

		printWriter.write( TreePrinterHelper.INSTANCE.generateNodePrefix( depth ) );


		if ( ScalarReturn.class.isInstance( rootReturn ) ) {
			printWriter.println( extractDetails( (ScalarReturn) rootReturn ) );
		}
		else if ( EntityReturn.class.isInstance( rootReturn ) ) {
			final EntityReturn entityReturn = (EntityReturn) rootReturn;
			printWriter.println( extractDetails( entityReturn ) );
			writeEntityReferenceFetches( entityReturn, depth+1, printWriter );
		}
		else if ( CollectionReference.class.isInstance( rootReturn ) ) {
			final CollectionReference collectionReference = (CollectionReference) rootReturn;
			printWriter.println( extractDetails( collectionReference ) );
			writeCollectionReferenceFetches( collectionReference, depth+1, printWriter );
		}

		printWriter.flush();
	}
