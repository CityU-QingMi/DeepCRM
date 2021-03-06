	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof IndexedListJoinColumnBidirectionalRefEdEntity) ) {
			return false;
		}

		IndexedListJoinColumnBidirectionalRefEdEntity that = (IndexedListJoinColumnBidirectionalRefEdEntity) o;

		if ( data != null ? !data.equals( that.data ) : that.data != null ) {
			return false;
		}
		//noinspection RedundantIfStatement
		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}

		return true;
	}
