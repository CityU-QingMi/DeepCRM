	public boolean equals(Object obj) {
		//firstName and lastName are expected to be set in this implem
		if ( obj != null && obj instanceof WomanPk ) {
			WomanPk other = (WomanPk) obj;
			return getFirstName().equals( other.getFirstName() )
					&& getLastName().equals( other.getLastName() );
		}
		else {
			return false;
		}
	}
