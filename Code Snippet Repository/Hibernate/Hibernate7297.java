	@Test
	@TestForIssue( jiraKey = "")
	public void testAddQuestionAfterSessionIsClosed(){
		Session session = openSession();
		Transaction transaction = session.beginTransaction();

		Quizz quizz = session.get( Quizz.class, 1 );
		assertThat(  "expected 3 questions", quizz.getQuestions().size(), is(3) );
		transaction.commit();
		session.close();

		quizz.addQuestion(  new Question( 4, "question 4" ) );
		assertThat(  "expected 4 questions", quizz.getQuestions().size(), is(4) );

		quizz.addQuestion(  1, new Question( 5, "question 5" ) );
		assertThat(  "expected 5 questions", quizz.getQuestions().size(), is(5) );
	}
