		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			ImmutableState that = (ImmutableState) o;

			return getState().equals( that.getState() );

		}
