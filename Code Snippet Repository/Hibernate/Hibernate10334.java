	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof ListJoinColumnBidirectionalInheritanceRefEdParentEntity) ) {
			return false;
		}

		ListJoinColumnBidirectionalInheritanceRefEdParentEntity that = (ListJoinColumnBidirectionalInheritanceRefEdParentEntity) o;

		if ( parentData != null ? !parentData.equals( that.parentData ) : that.parentData != null ) {
			return false;
		}
		//noinspection RedundantIfStatement
		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}

		return true;
	}
