	public String toFragmentString() {
		StringBuilder buf = new StringBuilder( columns.size() * 10 );
		Iterator<String> iter = columns.iterator();
		Iterator<String> columnAliasIter = columnAliases.iterator();
		//HashMap columnsUnique = new HashMap();
		HashSet<String> columnsUnique = new HashSet<String>();
		if (usedAliases!=null) {
			columnsUnique.addAll( Arrays.asList(usedAliases) );
		}
		while ( iter.hasNext() ) {
			String column = iter.next();
			String columnAlias = columnAliasIter.next();
			//TODO: eventually put this back in, once we think all is fixed
			//Object otherAlias = columnsUnique.put(qualifiedColumn, columnAlias);
/**/
/**/
/**/
			if ( columnsUnique.add(columnAlias) ) {
				buf.append(", ")
					.append(column)
					.append(" as ");
				if (suffix==null) {
					buf.append(columnAlias);
				}
				else {
					buf.append( new Alias(suffix).toAliasString(columnAlias) );
				}
			}
		}
		if (extraSelectList!=null) {
			buf.append(", ")
				.append(extraSelectList);
		}
		return buf.toString();
	}
