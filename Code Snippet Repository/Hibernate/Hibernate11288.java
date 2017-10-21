	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof BabyCompleteEntity) ) {
			return false;
		}
		if ( !super.equals( o ) ) {
			return false;
		}

		BabyCompleteEntity that = (BabyCompleteEntity) o;

		if ( baby != null ? !baby.equals( that.baby ) : that.baby != null ) {
			return false;
		}

		return true;
	}
