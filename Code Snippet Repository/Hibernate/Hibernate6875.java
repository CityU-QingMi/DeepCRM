	public boolean equals(Object obj) {
		//firstName and lastName are expected to be set in this implem
		if ( obj != null && obj instanceof ParentPk ) {
			ParentPk other = (ParentPk) obj;
			return firstName.equals( other.firstName )
					&& lastName.equals( other.lastName )
					&& isMale == other.isMale;
		}
		else {
			return false;
		}
	}
