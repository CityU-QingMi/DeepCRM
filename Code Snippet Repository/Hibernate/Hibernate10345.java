	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity) ) {
			return false;
		}

		ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity that = (ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity) o;

		if ( data != null ? !data.equals( that.data ) : that.data != null ) {
			return false;
		}
		//noinspection RedundantIfStatement
		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}

		return true;
	}
