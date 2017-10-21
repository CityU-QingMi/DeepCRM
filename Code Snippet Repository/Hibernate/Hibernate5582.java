	@TestForIssue( jiraKey = "" )
    @Test
	public void test() {
		final Top top = new Top();
		;
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {

			entityManager.persist( top );
			// Flush 1
			entityManager.flush();

			Middle middle = new Middle( 1l );
			top.addMiddle( middle );
			middle.setTop( top );
			Bottom bottom = new Bottom();
			middle.setBottom( bottom );
			bottom.setMiddle( middle );

			Middle middle2 = new Middle( 2l );
			top.addMiddle( middle2 );
			middle2.setTop( top );
			Bottom bottom2 = new Bottom();
			middle2.setBottom( bottom2 );
			bottom2.setMiddle( middle2 );
			// Flush 2
			entityManager.flush();
		} );

		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {

			Top finded = entityManager.find( Top.class, top.getId() );

			assertEquals( 2, finded.getMiddles().size() );
			for ( Middle loadedMiddle : finded.getMiddles() ) {
				assertSame( finded, loadedMiddle.getTop() );
				assertNotNull( loadedMiddle.getBottom() );
			}
			entityManager.remove( finded );
		} );
	}
