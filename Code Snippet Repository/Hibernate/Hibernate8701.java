		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			CardField cardField = (CardField) o;

			if ( primaryKey != null ? !primaryKey.equals( cardField.primaryKey ) : cardField.primaryKey != null ) {
				return false;
			}

			return true;
		}
