	public Sequence createSequence(Identifier logicalName, int initialValue, int increment) {
		if ( sequences.containsKey( logicalName ) ) {
			throw new HibernateException( "Sequence was already registered with that name [" + logicalName.toString() + "]" );
		}

		final Identifier physicalName = database.getPhysicalNamingStrategy().toPhysicalSequenceName( logicalName, database.getJdbcEnvironment() );

		Sequence sequence = new Sequence(
				this.physicalName.getCatalog(),
				this.physicalName.getSchema(),
				physicalName,
				initialValue,
				increment
		);
		sequences.put( logicalName, sequence );
		return sequence;
	}
