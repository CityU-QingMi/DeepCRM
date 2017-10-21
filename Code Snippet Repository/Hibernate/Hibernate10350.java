	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof BidirectionalEagerAnnotationRefEdOneToOne) ) {
			return false;
		}

		BidirectionalEagerAnnotationRefEdOneToOne that = (BidirectionalEagerAnnotationRefEdOneToOne) o;

		if ( data != null ? !data.equals( that.data ) : that.data != null ) {
			return false;
		}
		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}

		return true;
	}
