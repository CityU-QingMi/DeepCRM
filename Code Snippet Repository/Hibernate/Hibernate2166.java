	@Override
	public String format(String sql) {
		if ( StringHelper.isEmpty( sql ) ) {
			return sql;
		}

		if ( sql.toLowerCase(Locale.ROOT).startsWith( "create table" ) ) {
			return formatCreateTable( sql );
		}
		else if ( sql.toLowerCase(Locale.ROOT).startsWith( "create" ) ) {
			return sql;
		}
		else if ( sql.toLowerCase(Locale.ROOT).startsWith( "alter table" ) ) {
			return formatAlterTable( sql );
		}
		else if ( sql.toLowerCase(Locale.ROOT).startsWith( "comment on" ) ) {
			return formatCommentOn( sql );
		}
		else {
			return INITIAL_LINE + sql;
		}
	}
