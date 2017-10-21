	@Test
	@TestForIssue(jiraKey = "")
	public void testSequencePerEntity() {
		Session session = openSession();
		session.beginTransaction();
		DedicatedSequenceEntity1 entity1 = new DedicatedSequenceEntity1();
		DedicatedSequenceEntity2 entity2 = new DedicatedSequenceEntity2();
		session.persist( entity1 );
		session.persist( entity2 );
		session.getTransaction().commit();

		assertEquals( 1, entity1.getId().intValue() );
		assertEquals( 1, entity2.getId().intValue() );

		session.close();
	}
