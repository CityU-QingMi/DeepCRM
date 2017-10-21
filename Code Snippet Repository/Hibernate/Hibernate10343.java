	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof ChildIndexedListJoinColumnBidirectionalRefIngEntity) ) {
			return false;
		}
		if ( !super.equals( o ) ) {
			return false;
		}

		ChildIndexedListJoinColumnBidirectionalRefIngEntity that = (ChildIndexedListJoinColumnBidirectionalRefIngEntity) o;

		if ( data2 != null ? !data2.equals( that.data2 ) : that.data2 != null ) {
			return false;
		}

		return true;
	}
