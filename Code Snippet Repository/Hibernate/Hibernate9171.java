	@Test
	@TestForIssue(jiraKey = "")
	public void testQuotedUniqueConstraint() {
		Iterator<UniqueKey> itr = metadata().getEntityBinding( Person.class.getName() )
				.getTable().getUniqueKeyIterator();
		while ( itr.hasNext() ) {
			UniqueKey uk = itr.next();
			assertEquals( uk.getColumns().size(), 1 );
			assertEquals( uk.getColumn( 0 ).getName(),  "name");
			return;
		}
		fail( "GLOBALLY_QUOTED_IDENTIFIERS caused the unique key creation to fail." );
	}
