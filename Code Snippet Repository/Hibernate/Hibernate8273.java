	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		LineItemPK that = ( LineItemPK ) o;

		if ( !order.equals( that.order ) ) {
			return false;
		}
		if ( !productCode.equals( that.productCode ) ) {
			return false;
		}

		return true;
	}
