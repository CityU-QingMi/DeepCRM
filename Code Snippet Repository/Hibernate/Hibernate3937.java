	@SuppressWarnings("")
	public Set<Table> getIdentityTables() {
		Set<Table> tables = new HashSet<Table>();
		Iterator iter = getSubclassClosureIterator();
		while ( iter.hasNext() ) {
			PersistentClass clazz = (PersistentClass) iter.next();
			if ( clazz.isAbstract() == null || !clazz.isAbstract().booleanValue() ) {
				tables.add( clazz.getIdentityTable() );
			}
		}
		return tables;
	}
