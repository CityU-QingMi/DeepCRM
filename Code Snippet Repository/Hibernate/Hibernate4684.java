	public Select setOuterJoins(String outerJoinsAfterFrom, String outerJoinsAfterWhere) {
		this.outerJoinsAfterFrom = outerJoinsAfterFrom;

		// strip off any leading 'and' token
		String tmpOuterJoinsAfterWhere = outerJoinsAfterWhere.trim();
		if ( tmpOuterJoinsAfterWhere.startsWith("and") ) {
			tmpOuterJoinsAfterWhere = tmpOuterJoinsAfterWhere.substring(4);
		}
		this.outerJoinsAfterWhere = tmpOuterJoinsAfterWhere;

		this.guesstimatedBufferSize += outerJoinsAfterFrom.length() + outerJoinsAfterWhere.length();
		return this;
	}
