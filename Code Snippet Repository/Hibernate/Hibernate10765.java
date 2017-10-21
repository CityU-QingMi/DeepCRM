		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			Project project = (Project) o;

			if ( id != null ? !id.equals( project.id ) : project.id != null ) {
				return false;
			}
			if ( name != null ? !name.equals( project.name ) : project.name != null ) {
				return false;
			}
			return type != null ? type.equals( project.type ) : project.type == null;
		}
