		@Override
		public String processSql(String sql, RowSelection selection) {
			final boolean hasOffset = LimitHelper.hasFirstRow( selection );
			if ( hsqldbVersion < 200 ) {
				return new StringBuilder( sql.length() + 10 )
						.append( sql )
						.insert(
								sql.toLowerCase(Locale.ROOT).indexOf( "select" ) + 6,
								hasOffset ? " limit ? ?" : " top ?"
						)
						.toString();
			}
			else {
				return sql + (hasOffset ? " offset ? limit ?" : " limit ?");
			}
		}
