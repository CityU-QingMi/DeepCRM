		@Override
		public boolean equals(Object obj) {
			if ( this == obj ) {
				return true;
			}
			if ( obj == null ) {
				return false;
			}
			if ( getClass() != obj.getClass() ) {
				return false;
			}
			User other = (User) obj;
			if ( email == null ) {
				if ( other.email != null ) {
					return false;
				}
			}
			else if ( !email.equals( other.email ) ) {
				return false;
			}
			return true;
		}
