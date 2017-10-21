	private void verifyRevEndTimeStamps(
			String debugInfo,
			List<Map<String, Object>> revisionEntities) {
		for ( Map<String, Object> revisionEntity : revisionEntities ) {

			Date revendTimestamp = (Date) revisionEntity
					.get( revendTimestampColumName );
			CustomDateRevEntity revEnd = (CustomDateRevEntity) revisionEntity
					.get( "REVEND" );

			if ( revendTimestamp == null ) {
				assert revEnd == null;
			}
			else {
				assert revendTimestamp.getTime() == revEnd.getDateTimestamp().getTime();
			}
		}
	}
