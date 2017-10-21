		@Override
		public boolean equals(Object object) {
			if ( object == this ) {
				return true;
			}
			if ( object == null || !( object instanceof Task ) ) {
				return false;
			}
			Task task = (Task) object;
			if ( id != null ? !id.equals( task.id ) : task.id != null ) {
				return false;
			}
			return !( name != null ? !name.equals( task.name ) : task.name != null );
		}
