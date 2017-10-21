		public <X> X get(TupleElement<X> tupleElement) {
			int index = tupleElements.indexOf( tupleElement );
			if ( index < 0 ) {
				throw new IllegalArgumentException(
						"Requested tuple element did not correspond to element in the result tuple"
				);
			}
			// index should be "in range" by nature of size check in ctor
			return (X) tuples[index];
		}
