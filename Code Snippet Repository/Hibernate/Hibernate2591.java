		@Override
		public void clause(String clause) {
			if ( startedType ) {
				if ( castTargetType == null ) {
					castTargetType = clause;
				}
				else {
					castTargetType += clause;
				}
			}
			else {
				if ( castExpression == null ) {
					castExpression = clause;
				}
				else {
					castExpression += clause;
				}
			}
		}
