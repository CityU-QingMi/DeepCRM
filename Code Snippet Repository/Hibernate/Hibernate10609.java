	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof PurchaseOrder) ) {
			return false;
		}

		PurchaseOrder that = (PurchaseOrder) o;

		if ( getComment() != null ? !getComment().equals( that.getComment() ) : that.getComment() != null ) {
			return false;
		}
		if ( getId() != null ? !getId().equals( that.getId() ) : that.getId() != null ) {
			return false;
		}
		if ( getItem() != null ? !getItem().equals( that.getItem() ) : that.getItem() != null ) {
			return false;
		}

		return true;
	}
