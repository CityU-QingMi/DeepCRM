		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			DesignContract that = (DesignContract) o;

			if ( pk != null ? !pk.equals( that.pk ) : that.pk != null ) {
				return false;
			}
			return goal != null ? goal.equals( that.goal ) : that.goal == null;
		}
