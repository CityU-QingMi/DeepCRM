	@Override
	protected void afterEntityManagerFactoryBuilt() {
		doInJPA( this::entityManagerFactory, em -> {
			UserStatistic statistic = new UserStatistic();
			statistic.id = 1L;
			statistic.commentCount = 7;
			User user = new User();
			user.id = 1L;
			user.userStatistic = statistic;

			em.persist( statistic );
			em.persist( user );
		} );
	}
