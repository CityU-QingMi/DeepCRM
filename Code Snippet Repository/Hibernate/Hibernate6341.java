	@Test
	public void testPersistCascadeToEmbedded() {
		Session sess = openSession();
		try {
			final Transaction trx  = sess.beginTransaction();
			try {
				PersonPair personPair = new PersonPair(new Person("PERSON NAME 1"), new Person("PERSON NAME 2"));
				sess.persist( new CodedPairHolder( "CODE", personPair ) );
				sess.flush();
			} finally {
				trx.rollback();
			}
		} finally {
			sess.close();
		}
	}
