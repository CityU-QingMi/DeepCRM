	@Override
	public void addJpaIndexHolders(Table table, List<JPAIndexHolder> holders) {
		List<JPAIndexHolder> holderList = null;

		if ( jpaIndexHoldersByTable == null ) {
			jpaIndexHoldersByTable = new HashMap<Table, List<JPAIndexHolder>>();
		}
		else {
			holderList = jpaIndexHoldersByTable.get( table );
		}

		if ( holderList == null ) {
			holderList = new ArrayList<JPAIndexHolder>();
			jpaIndexHoldersByTable.put( table, holderList );
		}

		holderList.addAll( holders );
	}
