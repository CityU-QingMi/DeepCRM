	@Test
	public void addQuestionToDetachedQuizz() {
		Session session = openSession();
		session.beginTransaction();
		Quizz quizz = session.get( Quizz.class, 1 );
		session.getTransaction().commit();
		session.close();

		assertFalse( ( (PersistentCollection) quizz.getQuestions() ).wasInitialized() );

		try {
			// this is the crux of the comment on HHH-9195 in regard to uninitialized, detached collections and
			// not allowing additions
			quizz.addQuestion( new Question( 4, "question 4" ) );

			// indexed-addition should fail
			quizz.addQuestion( 1, new Question( 5, "question that should be at index 1" ) );
			fail( "Expecting a failure" );
		}
		catch (LazyInitializationException ignore) {
			// expected
		}

//		session = openSession();
//		session.beginTransaction();
//		session.merge( quizz );
//		session.getTransaction().commit();
//		session.close();
//
//		session = openSession();
//		session.getTransaction().begin();
//		quizz = session.get( Quizz.class,  1);
//		assertEquals( 5, quizz.getQuestions().size() );
//		assertEquals( 5, quizz.getQuestions().get( 1 ).getId().longValue() );
//		session.getTransaction().commit();
//		session.close();
	}
