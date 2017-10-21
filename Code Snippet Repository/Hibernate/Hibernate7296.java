	@Test
	public void addQuestionWithIndexAndInitializeTheListShouldAddQuestionAtSpecifiedIndex() {
		Session session = openSession();
		Transaction transaction = session.beginTransaction();

		Quizz quizz = session.get( Quizz.class, 1 );
		quizz.addQuestionAndInitializeLazyList( 1, new Question( 4, "question that should be at index 1" ) );

		transaction.commit();
		session.close();

		session = openSession();
		transaction = session.beginTransaction();

		quizz = session.get( Quizz.class, 1 );

		assertEquals( 4, quizz.getQuestions().size());
		assertEquals( 4, quizz.getQuestions().get(1).getId().longValue() );

		transaction.commit();
		session.close();
	}
