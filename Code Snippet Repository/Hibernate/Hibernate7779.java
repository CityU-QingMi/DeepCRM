	@Test
	@TestForIssue(jiraKey="")
	public void testMap() {
		Session session1 = openSession();
		Transaction tx1 = session1.beginTransaction();
		Parent parent = new Parent ();		
		Child child = new Child ();
		child.setFirstName("Ben");
		parent.getChildren().put(child.getFirstName(), child);
		child.setParent(parent);		
		session1.save(parent);
		tx1.commit();
		session1.close();
		// END PREPARE SECTION
		
		Session session2 = openSession();
		Parent parent2 = (Parent)session2.get(Parent.class, parent.getId());
		Child child2 = parent2.getChildren().get(child.getFirstName()); // causes SQLGrammarException because of wrong condition: 	where child0_.PARENT_ID=? and child0_.null=?
		assertNotNull(child2);
		session2.close();
	}
