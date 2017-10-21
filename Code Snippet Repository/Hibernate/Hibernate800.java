		@Override
		public int compareTo(Name that) {
			// per Comparable, the incoming Name cannot be null.  However, its catalog/schema might be
			// so we need to account for that.
			int catalogCheck = ComparableHelper.compare( this.getCatalog(), that.getCatalog() );
			if ( catalogCheck != 0 ) {
				return catalogCheck;
			}

			return ComparableHelper.compare( this.getSchema(), that.getSchema() );
		}
