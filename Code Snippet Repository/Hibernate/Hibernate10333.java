	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		if ( !super.equals( o ) ) {
			return false;
		}

		ListJoinColumnBidirectionalInheritanceRefEdChildEntity that = (ListJoinColumnBidirectionalInheritanceRefEdChildEntity) o;

		//noinspection RedundantIfStatement
		if ( childData != null ? !childData.equals( that.childData ) : that.childData != null ) {
			return false;
		}

		return true;
	}
