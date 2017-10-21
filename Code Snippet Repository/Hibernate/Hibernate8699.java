		public boolean equals(Object o) {
			if ( this == o ) return true;
			if ( !( o instanceof Parent ) ) return false;

			final Parent parent = (Parent) o;

			if ( !id.equals( parent.id ) ) return false;

			return true;
		}
