	public String toFragmentString() {
		StringBuilder buf = new StringBuilder( cases.size() * 15 + 10 );
		StringBuilder buf2 = new StringBuilder( cases.size() );

		Iterator iter = cases.entrySet().iterator();
		while ( iter.hasNext() ) {
			Map.Entry me = (Map.Entry) iter.next();
			buf.append(" casewhen(")
				.append( me.getKey() )
				.append(" is not null")
				.append(", ")
				.append( me.getValue() )
				.append(", ");
			buf2.append(")");
		}

		buf.append("-1"); //null caused some problems
		buf.append( buf2.toString() );
		if ( returnColumnName!=null ) {
			buf.append(" as ")
				.append(returnColumnName);
		}
		return buf.toString();
	}
