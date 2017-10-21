	@Test
	@TestForIssue(jiraKey = "")
	public void testSetPropertiesMapNotContainingAllTheParameters() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		try {
			Human human = new Human();
			human.setNickName( "nick" );
			human.setIntValue( 1 );
			s.save( human );

			Map parameters = new HashMap();
			parameters.put( "nickNames", "nick" );

			List<Integer> intValues = new ArrayList<>();
			intValues.add( 1 );
			Query q = s.createQuery(
					"from Human h where h.nickName in (:nickNames) and h.intValue in (:intValues)" );
			q.setParameterList( "intValues" , intValues);
			q.setProperties( (parameters) );
			assertThat( q.list().size(), is( 1 ) );

			s.delete( human );
			t.commit();
		}
		catch (Exception e) {
			if ( session.getTransaction().getStatus() == TransactionStatus.ACTIVE ) {
				session.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			s.close();
		}
	}
