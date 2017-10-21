		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			MutableState that = (MutableState) o;

			return getState() != null ? getState().equals( that.getState() ) : that.getState() == null;

		}
