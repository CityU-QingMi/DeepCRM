		public static FakeRelationChange merge(FakeRelationChange first, FakeRelationChange second) {
			if ( first == null ) {
				return second;
			}
			if ( second == null ) {
				return first;
			}

/**/
/**/
/**/
/**/
/**/
/**/
/**/
			if ( first.getRevisionType() == RevisionType.DEL || second.getRevisionType() == RevisionType.ADD ) {
				return second;
			}
			else {
				return first;
			}
		}
