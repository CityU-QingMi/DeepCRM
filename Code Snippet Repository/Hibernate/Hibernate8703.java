		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			Card card = (Card) o;

			if ( !id.equals( card.id ) ) {
				return false;
			}

			return true;
		}
