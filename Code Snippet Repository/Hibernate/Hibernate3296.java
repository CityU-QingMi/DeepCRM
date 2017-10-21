		public boolean hasNext() {
			while ( nextEntry != null ) {
				if ( nextEntry.key() != null ) {
					return true;
				}
				advance();
			}

			return false;
		}
