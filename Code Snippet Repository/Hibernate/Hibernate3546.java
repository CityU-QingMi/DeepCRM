	protected StringBuilder whereString(String alias, String[] columnNames, String subselect, int batchSize) {
		if (subselect==null) {
			return whereString(alias, columnNames, batchSize);
		}
		else {
			StringBuilder buf = new StringBuilder();
			if (columnNames.length>1) {
				buf.append('(');
			}
			buf.append( StringHelper.join(", ", StringHelper.qualify(alias, columnNames) ) );
			if (columnNames.length>1) {
				buf.append(')');
			}
			buf.append(" in ")
				.append('(')
				.append(subselect) 
				.append(')');
			return buf;
		}
	}
