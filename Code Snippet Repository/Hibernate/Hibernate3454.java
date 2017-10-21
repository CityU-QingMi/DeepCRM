		public Object get(String alias) {
			int index = -1;
			if ( alias != null ) {
				alias = alias.trim();
				if ( alias.length() > 0 ) {
					int i = 0;
					for ( TupleElement selection : (List<TupleElement>) tupleElements ) {
						if ( alias.equals( selection.getAlias() ) ) {
							index = i;
							break;
						}
						i++;
					}
				}
			}
			if ( index < 0 ) {
				throw new IllegalArgumentException(
						"Given alias [" + alias + "] did not correspond to an element in the result tuple"
				);
			}
			// index should be "in range" by nature of size check in ctor
			return tuples[index];
		}
