	@Test
	public void testPersistCascadeToSetOfEmbedded() {
		Session sess = openSession();
		try {
			final Transaction trx  = sess.beginTransaction();
			try {
				final Set<PersonPair> setOfPairs = new HashSet<PersonPair>();
				setOfPairs.add(new PersonPair(new Person("PERSON NAME 1"), new Person("PERSON NAME 2")));
				sess.persist( new CodedPairSetHolder( "CODE", setOfPairs ) );
				sess.flush();
			} finally {
				trx.rollback();
			}
		} finally {
			sess.close();
		}
	}
