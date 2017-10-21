	public JoinSequence addCondition(String alias, String[] columns, String condition) {
		for ( String column : columns ) {
			conditions.append( " and " )
					.append( alias )
					.append( '.' )
					.append( column )
					.append( condition );
		}
		return this;
	}
