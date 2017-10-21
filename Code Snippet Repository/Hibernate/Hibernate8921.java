	@Test
	@FailureExpected( jiraKey = "" )
	public void test() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			Parent a = new Parent();
			a.id = 1L;
			Child b = new Child();
			b.id = 1L;
			a.setChild(b);
			b.setParent(a);

			entityManager.persist(a);
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Parent a = entityManager.find(Parent.class, 1L);

			entityManager.remove(a);
		} );
	}
