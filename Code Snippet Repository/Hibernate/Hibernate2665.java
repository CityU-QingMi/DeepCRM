	private FromElement initializeJoin(
			String path,
			FromElement destination,
			JoinSequence joinSequence,
			String[] columns,
			FromElement origin,
			boolean manyToMany) {
		destination.setType( JOIN_FRAGMENT );
		destination.setJoinSequence( joinSequence );
		destination.setColumns( columns );
		destination.setOrigin( origin, manyToMany );
		fromClause.addJoinByPathMap( path, destination );
		return destination;
	}
