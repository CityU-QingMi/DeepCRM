	@Test
	public void testBasicUsageInJoin() {
		// todo : assert invalid naming of non-subclasses in TREAT statement
		Session s = openSession();

		s.createQuery( "from DiscriminatorEntity e join treat(e.other as DiscriminatorEntitySubclass) o" ).list();
		s.createQuery( "from DiscriminatorEntity e join treat(e.other as DiscriminatorEntitySubSubclass) o" ).list();
		s.createQuery( "from DiscriminatorEntitySubclass e join treat(e.other as DiscriminatorEntitySubSubclass) o" ).list();

		s.createQuery( "from JoinedEntity e join treat(e.other as JoinedEntitySubclass) o" ).list();
		s.createQuery( "from JoinedEntity e join treat(e.other as JoinedEntitySubSubclass) o" ).list();
		s.createQuery( "from JoinedEntitySubclass e join treat(e.other as JoinedEntitySubSubclass) o" ).list();

		s.close();
	}
