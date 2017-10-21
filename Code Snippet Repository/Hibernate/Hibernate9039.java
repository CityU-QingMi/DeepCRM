    @Test
    @TestForIssue( jiraKey = "")
    public void testSysRefCursorAsOutParameter() {

        doInJPA( this::entityManagerFactory, entityManager -> {
            StoredProcedureQuery function = entityManager.createNamedStoredProcedureQuery("singleRefCursor");

            function.execute();

            assertFalse( function.hasMoreResults() );
            Long value = null;

			try ( ResultSet resultSet = (ResultSet) function.getOutputParameterValue( 1 ) ) {
				while ( resultSet.next() ) {
					value = resultSet.getLong( 1 );
				}
			}
			catch (SQLException e) {
				fail(e.getMessage());
			}

			assertEquals( Long.valueOf( 1 ), value );
        } );
    }
