	public String getAddForeignKeyConstraintString(
			String constraintName,
			String foreignKeyDefinition) {
		return new StringBuilder( 30 )
				.append( " add constraint " )
				.append( quote( constraintName ) )
				.append( " " )
				.append( foreignKeyDefinition )
				.toString();
	}
