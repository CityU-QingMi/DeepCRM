	@Before
	public void before() {
		Session session = sessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Quizz quizz = new Quizz( 1 );
			session.persist( quizz );
			quizz.addQuestion( new Question( 1, "question 1" ) );
			quizz.addQuestion( new Question( 2, "question 2" ) );
			quizz.addQuestion( new Question( 3, "question 3" ) );

			transaction.commit();
		}
		finally {
			session.close();
		}
	}
