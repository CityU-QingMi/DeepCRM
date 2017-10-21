	private void verifyRevEndTimeStamps(String debugInfo, List<Map<String, Object>> revisionEntities) {
		for ( Map<String, Object> revisionEntity : revisionEntities ) {
			Date revendTimestamp = (Date) revisionEntity.get( revendTimestampColumName );
			SequenceIdRevisionEntity revEnd = (SequenceIdRevisionEntity) revisionEntity.get( "REVEND" );

			if ( revendTimestamp == null ) {
				Assert.assertNull( revEnd );
			}
			else {
				if ( getDialect() instanceof MySQL5Dialect &&
						!( getDialect() instanceof MySQL57Dialect || getDialect() instanceof MariaDB53Dialect) ) {
					// MySQL5 DATETIME column type does not contain milliseconds.
					// MySQL 5.7 supports milliseconds and when MySQL57InnoDBDialect is used, it is assumed that
					// the column is defined as DATETIME(6).
					Assert.assertEquals(
							revendTimestamp.getTime(),
							(revEnd.getTimestamp() - (revEnd.getTimestamp() % 1000))
					);
				}
				else if ( getDialect() instanceof SybaseASE15Dialect ) {
					// Sybase "DATETIME values are accurate to 1/300 second on platforms that support this level of granularity".
					Assert.assertEquals(
							revendTimestamp.getTime() / 1000.0, revEnd.getTimestamp() / 1000.0, 1.0 / 300.0
					);
				}
				else {
					Assert.assertEquals( revendTimestamp.getTime(), revEnd.getTimestamp() );
				}
			}
		}
	}
