	private void mergeJoins(JoinFragment ojf) throws MappingException, QueryException {

		Iterator iter = joins.entrySet().iterator();
		while ( iter.hasNext() ) {
			Map.Entry me = (Map.Entry) iter.next();
			String name = (String) me.getKey();
			JoinSequence join = (JoinSequence) me.getValue();
			join.setSelector(
					new JoinSequence.Selector() {
						@Override
						public boolean includeSubclasses(String alias) {
							return returnedTypes.contains( alias ) && !isShallowQuery();
						}
					}
			);

			if ( typeMap.containsKey( name ) ) {
				ojf.addFragment( join.toJoinFragment( enabledFilters, true ) );
			}
			else if ( collections.containsKey( name ) ) {
				ojf.addFragment( join.toJoinFragment( enabledFilters, true ) );
			}
		}

	}
