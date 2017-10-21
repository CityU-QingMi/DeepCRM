		@Override
		public boolean equals(Object object) {
			if ( object == this ) {
				return true;
			}
			if ( object == null || !( object instanceof Component ) ) {
				return false;
			}
			Component that = (Component) object;
			return !( name != null ? !name.equals( that.name ) : that.name != null );
		}
