		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			final PersistentCollectionChangeWorkUnitId that = (PersistentCollectionChangeWorkUnitId) o;

			if ( ownerId != null ? !ownerId.equals( that.ownerId ) : that.ownerId != null ) {
				return false;
			}
			//noinspection RedundantIfStatement
			if ( role != null ? !role.equals( that.role ) : that.role != null ) {
				return false;
			}

			return true;
		}
