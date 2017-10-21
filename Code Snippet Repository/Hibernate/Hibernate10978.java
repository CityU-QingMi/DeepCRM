	@Test
	public void initData() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			final Contract contract = new Contract( 1 );
			final Design design = new Design( 1 );
			final DesignContract designContract = new DesignContract( contract, design );
			designContract.setGoal( 25d );
			contract.getDesigns().add( designContract );
			entityManager.persist( design );
			entityManager.persist( contract );
		} );
	}
