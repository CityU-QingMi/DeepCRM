	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::BytecodeEnhancement-dirty-tracking-bidirectional-incorrect-usage-example[]
			Person person = new Person();
			person.setName( "John Doe" );

			Book book = new Book();
			person.getBooks().add( book );
			try {
				book.getAuthor().getName();
			}
			catch (NullPointerException expected) {
				// This blows up ( NPE ) in normal Java usage
			}
			//end::BytecodeEnhancement-dirty-tracking-bidirectional-incorrect-usage-example[]
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::BytecodeEnhancement-dirty-tracking-bidirectional-correct-usage-example[]
			Person person = new Person();
			person.setName( "John Doe" );

			Book book = new Book();
			person.getBooks().add( book );
			book.setAuthor( person );

			book.getAuthor().getName();
			//end::BytecodeEnhancement-dirty-tracking-bidirectional-correct-usage-example[]
		} );
	}
