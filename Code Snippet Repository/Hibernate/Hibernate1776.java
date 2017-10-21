		@Override
		public String processSql(String sql, RowSelection selection) {
			final String soff = " offset " + selection.getFirstRow();
			final String slim = " fetch first " + getMaxOrLimit( selection ) + " rows only";
			final StringBuilder sb = new StringBuilder( sql.length() + soff.length() + slim.length() )
					.append( sql );
			if (LimitHelper.hasFirstRow( selection )) {
				sb.append( soff );
			}
			if (LimitHelper.hasMaxRows( selection )) {
				sb.append( slim );
			}
			return sb.toString();
		}
