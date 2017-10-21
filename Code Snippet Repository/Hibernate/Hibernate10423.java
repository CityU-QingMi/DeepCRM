		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			AbstractEntity that = (AbstractEntity) o;

			return id != null ? id.equals( that.id ) : that.id == null;
		}
