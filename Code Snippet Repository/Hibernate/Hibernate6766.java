	public boolean equals(Object obj) {
		//firstName and lastName are expected to be set in this implem
		if ( obj != null && obj instanceof ManPk ) {
			ManPk other = (ManPk) obj;
			return getFirstName().equals( other.getFirstName() )
					&& getLastName().equals( other.getLastName() )
					&& isElder() == other.isElder();
		}
		else {
			return false;
		}
	}
