	public void testOrderByWithDottedNotation() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		BugSystem bs = new BugSystem();
		HashSet<Bug> set = new HashSet<Bug>();

		Bug bug = new Bug();
		bug.setDescription("JPA-2 locking");
		bug.setSummary("JPA-2 impl locking");
		Person p = new Person();
		p.setFirstName("Scott");
		p.setLastName("Marlow");
		bug.setReportedBy(p);
		set.add(bug);

		bug = new Bug();
		bug.setDescription("JPA-2 annotations");
		bug.setSummary("JPA-2 impl annotations");
		p = new Person();
		p.setFirstName("Emmanuel");
		p.setLastName("Bernard");
		bug.setReportedBy(p);
		set.add(bug);

		bug = new Bug();
		bug.setDescription("Implement JPA-2 criteria");
		bug.setSummary("JPA-2 impl criteria");
		p = new Person();
		p.setFirstName("Steve");
		p.setLastName("Ebersole");
		bug.setReportedBy(p);
		set.add(bug);

		bs.setBugs(set);
		s.persist(bs);
		tx.commit();

		tx = s.beginTransaction();
		s.clear();
		bs = (BugSystem) s.get(BugSystem.class,bs.getId());
		Assert.assertTrue("has three bugs", bs.getBugs().size() == 3);
		Iterator iter = bs.getBugs().iterator();
		Assert.assertEquals( "Emmanuel", ((Bug)iter.next()).getReportedBy().getFirstName() );
		Assert.assertEquals( "Steve", ((Bug)iter.next()).getReportedBy().getFirstName() );
		Assert.assertEquals( "Scott", ((Bug)iter.next()).getReportedBy().getFirstName() );
		tx.commit();
		s.close();

	}
