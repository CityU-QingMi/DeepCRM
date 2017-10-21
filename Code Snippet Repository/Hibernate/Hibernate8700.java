		public boolean equals(Object o) {
			if ( this == o ) return true;
			if ( !( o instanceof ParentPK ) ) return false;

			final ParentPK parentPk = (ParentPK) o;

			if ( !firstName.equals( parentPk.firstName ) ) return false;
			if ( !lastName.equals( parentPk.lastName ) ) return false;

			return true;
		}
