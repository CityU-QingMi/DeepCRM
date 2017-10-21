		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			CardFieldPK that = (CardFieldPK) o;

			if ( fieldNumber != that.fieldNumber ) {
				return false;
			}
			if ( card != null ? !card.equals( that.card ) : that.card != null ) {
				return false;
			}

			return true;
		}
