	public void build(StringBuilder sb, Map<String, Object> queryParamValues) {
		sb.append( "select " );
		if ( projections.size() > 0 ) {
			// all projections separated with commas
			StringTools.append( sb, projections.iterator(), ", " );
		}
		else {
			// all aliases separated with commas
			StringTools.append( sb, getSelectAliasList().iterator(), ", " );
		}
		sb.append( " from " );
		// all from entities with aliases
		boolean first = true;
		for (final JoinParameter joinParameter : froms) {
			joinParameter.appendJoin( first, sb, queryParamValues );
			first = false;
		}
		// where part - rootParameters
		first = true;
		for (final Parameters params : parameters) {
			if (!params.isEmpty()) {
				if (first) {
					sb.append( " where " );
					first = false;
				}
				else {
					sb.append( " and " );
				}
				params.build( sb, queryParamValues );
			}
		}
		// orders
		if ( orders.size() > 0 ) {
			sb.append( " order by " );
			StringTools.append( sb, getOrderList().iterator(), ", " );
		}
	}
