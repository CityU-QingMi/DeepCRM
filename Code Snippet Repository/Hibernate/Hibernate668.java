	@Override
	public void addUniqueConstraintHolders(Table table, List<UniqueConstraintHolder> uniqueConstraintHolders) {
		List<UniqueConstraintHolder> holderList = null;

		if ( uniqueConstraintHoldersByTable == null ) {
			uniqueConstraintHoldersByTable = new HashMap<Table, List<UniqueConstraintHolder>>();
		}
		else {
			holderList = uniqueConstraintHoldersByTable.get( table );
		}

		if ( holderList == null ) {
			holderList = new ArrayList<UniqueConstraintHolder>();
			uniqueConstraintHoldersByTable.put( table, holderList );
		}

		holderList.addAll( uniqueConstraintHolders );
	}
