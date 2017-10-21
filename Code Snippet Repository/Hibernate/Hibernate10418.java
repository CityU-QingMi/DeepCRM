		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( !(o instanceof AuditJoinTableInfo) ) {
				return false;
			}

			AuditJoinTableInfo that = (AuditJoinTableInfo) o;

			if ( inverseJoinColumnId != null ?
					!inverseJoinColumnId.equals( that.inverseJoinColumnId ) :
					that.inverseJoinColumnId != null ) {
				return false;
			}
			if ( joinColumnId != null ? !joinColumnId.equals( that.joinColumnId ) : that.joinColumnId != null ) {
				return false;
			}
			if ( name != null ? !name.equals( that.name ) : that.name != null ) {
				return false;
			}
			if ( revId != null ? !revId.equals( that.revId ) : that.revId != null ) {
				return false;
			}
			if ( revType != that.revType ) {
				return false;
			}

			return true;
		}
