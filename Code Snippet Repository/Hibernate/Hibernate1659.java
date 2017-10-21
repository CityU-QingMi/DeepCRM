	protected void appendPropertyCondition(
			String propertyName,
			Object propertyValue,
			Criteria criteria,
			CriteriaQuery cq,
			StringBuilder buf) {
		final Criterion condition;
		if ( propertyValue != null ) {
			final boolean isString = propertyValue instanceof String;
			if ( isLikeEnabled && isString ) {
				condition = new LikeExpression(
						propertyName,
						(String) propertyValue,
						matchMode,
						escapeCharacter,
						isIgnoreCaseEnabled
				);
			}
			else {
				condition = new SimpleExpression( propertyName, propertyValue, "=", isIgnoreCaseEnabled && isString );
			}
		}
		else {
			condition = new NullExpression(propertyName);
		}

		final String conditionFragment = condition.toSqlString( criteria, cq );
		if ( conditionFragment.trim().length() > 0 ) {
			if ( buf.length() > 1 ) {
				buf.append( " and " );
			}
			buf.append( conditionFragment );
		}
	}
