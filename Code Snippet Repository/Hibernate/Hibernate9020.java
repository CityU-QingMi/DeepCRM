	public void feedDatabase() {
		List<Tag> tags = new ArrayList<Tag>();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		for (int i = 0; i < 5; i++) {
			Tag tag = new Tag("Tag: " + UUID.randomUUID().toString());
			tags.add(tag);
			s.save(tag);
		}

		for (int i = 0; i < NUM_OF_USERS; i++) {
			Entry e = new Entry("Entry: " + UUID.randomUUID().toString());
			e.getTags().addAll(tags);
			s.save(e);
		}
		t.commit();
		s.close();
	}
