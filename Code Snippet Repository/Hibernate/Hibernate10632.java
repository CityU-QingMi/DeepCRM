	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof ChildPrimaryKeyJoinEntity) ) {
			return false;
		}
		if ( !super.equals( o ) ) {
			return false;
		}

		ChildPrimaryKeyJoinEntity childPrimaryKeyJoinEntity = (ChildPrimaryKeyJoinEntity) o;

		//noinspection RedundantIfStatement
		if ( namVal != null ?
				!namVal.equals( childPrimaryKeyJoinEntity.namVal ) :
				childPrimaryKeyJoinEntity.namVal != null ) {
			return false;
		}

		return true;
	}
