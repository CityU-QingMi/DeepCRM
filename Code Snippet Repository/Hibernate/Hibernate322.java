	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::basic-enums-Enumerated-ordinal-persistence-example[]
			Phone phone = new Phone( );
			phone.setId( 1L );
			phone.setNumber( "123-456-78990" );
			phone.setType( PhoneType.MOBILE );
			entityManager.persist( phone );
			//end::basic-enums-Enumerated-ordinal-persistence-example[]
		} );
	}
