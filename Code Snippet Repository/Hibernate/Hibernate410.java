    @Test
    public void test() {
        //tag::schema-generation-columns-unique-constraint-persist-example[]
        Author _author = doInJPA( this::entityManagerFactory, entityManager -> {
            Author author = new Author();
            author.setFirstName( "Vlad" );
            author.setLastName( "Mihalcea" );
            entityManager.persist( author );

            Book book = new Book();
            book.setTitle( "High-Performance Java Persistence" );
            book.setAuthor( author );
            entityManager.persist( book );

            return author;
        } );

        try {
            doInJPA( this::entityManagerFactory, entityManager -> {
				Book book = new Book();
				book.setTitle( "High-Performance Java Persistence" );
				book.setAuthor( _author );
				entityManager.persist( book );
			} );
        }
        catch (Exception expected) {
            assertNotNull( ExceptionUtil.findCause( expected, ConstraintViolationException.class ) );
        }
        //end::schema-generation-columns-unique-constraint-persist-example[]
    }
