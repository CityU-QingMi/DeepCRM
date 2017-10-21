	@Override
	public boolean equals(Object obj) {
		if ( obj == this ) {
			return true;
		}
		if ( obj == null || !( obj instanceof CustomerInventory ) ) {
			return false;
		}
		if ( this.id == ( ( CustomerInventory ) obj ).id ) {
			return true;
		}
		if ( this.id != null && ( ( CustomerInventory ) obj ).id == null ) {
			return false;
		}
		if ( this.id == null && ( ( CustomerInventory ) obj ).id != null ) {
			return false;
		}

		return this.id.equals( ( ( CustomerInventory ) obj ).id );
	}
