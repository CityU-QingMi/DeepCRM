	@Test
	@TestForIssue(jiraKey = "")
	public void testSharedSessionTransactionObserver() throws Exception {
		Session session = openSession();

		session.getTransaction().begin();

		Field field = null;
		Class<?> clazz = ((JdbcSessionOwner) session).getTransactionCoordinator().getClass();
		while (clazz != null) {
			try {
				field = clazz.getDeclaredField("observers");
				field.setAccessible(true);
				break;
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
		assertNotNull("Observers field was not found", field);

		assertEquals(0, ((Collection) field.get(((SessionImplementor) session).getTransactionCoordinator())).size());

		//open secondary sessions with managed options and immediately close
		Session secondarySession;
		for (int i = 0; i < 10; i++){
			secondarySession = session.sessionWithOptions()
					.connection()
					.flushMode( FlushMode.COMMIT )
					.autoClose( true )
					.openSession();

			//when the shared session is opened it should register an observer
			assertEquals(1, ((Collection) field.get(((JdbcSessionOwner) session).getTransactionCoordinator())).size());

			//observer should be released
			secondarySession.close();

			assertEquals(0, ((Collection) field.get(((JdbcSessionOwner) session).getTransactionCoordinator())).size());
		}
	}
