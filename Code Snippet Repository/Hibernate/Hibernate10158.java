	private List<String> getSelectAliasList() {
		final List<String> aliasList = new ArrayList<>();
		for ( JoinParameter from : froms ) {
			if ( from.isSelect() ) {
				aliasList.add( from.getAlias() );
			}
		}

		return aliasList;
	}
