		@Override
		public boolean equals(Object o) {
			if ( this == o ) {
				return true;
			}
			if ( o == null || getClass() != o.getClass() ) {
				return false;
			}

			SimpleId simpleId1 = (SimpleId) o;

			if ( getSimpleId() != null ? !getSimpleId().equals( simpleId1.getSimpleId() ) : simpleId1.getSimpleId() != null ) {
				return false;
			}
			return getCategoryId() != null ? getCategoryId().equals( simpleId1.getCategoryId() ) : simpleId1.getCategoryId() == null;
		}
