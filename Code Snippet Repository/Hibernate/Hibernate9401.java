		@Override
		public boolean equals(Object object) {
			if ( object == this ) {
				return true;
			}
			if ( object == null || !( object instanceof Task ) ) {
				return false;
			}
			Project project = (Project) object;
			if ( id != null ? !id.equals( project.id ) : project.id != null ) {
				return false;
			}
			return !( name != null ? !name.equals( project.name ) : project.name != null );
		}
