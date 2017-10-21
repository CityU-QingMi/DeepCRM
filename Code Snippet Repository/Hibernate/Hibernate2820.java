	private void closeExpression(QueryTranslatorImpl q, String lcToken) {
		if ( booleanTests.removeLast() ) { //it was a boolean expression

			if ( booleanTests.size() > 0 ) {
				// the next one up must also be
				booleanTests.removeLast();
				booleanTests.addLast( Boolean.TRUE );
			}

			// Add any joins
			appendToken( q, ( joins.removeLast() ).toString() );

		}
		else {
			StringBuilder join = joins.removeLast();
			joins.getLast().append( join.toString() );
		}

		if ( nots.removeLast() ) {
			negated = !negated;
		}

		if ( !")".equals( lcToken ) ) {
			appendToken( q, ")" );
		}
	}
