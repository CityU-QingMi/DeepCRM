	@Test
	public void testMemberOf() throws Exception {
		String hql = "from Animal a where a.mother in (from a.offspring)";
		//String hql = "from Animal a where a.mother in elements(a.offspring)";
		String ejbql = "select object(a) from Animal a where a.mother member of a.offspring";
		//parse(hql);
		//parse(ejbql);
		assertEjbqlEqualsHql( ejbql, hql );

		hql = "from Animal a where a.mother not in (from a.offspring)";
		//hql = "from Animal a where a.mother not in elements(a.offspring)";
		ejbql = "select object(a) from Animal a where a.mother not member of a.offspring";
		//parse(hql);
		//parse(ejbql);
		assertEjbqlEqualsHql( ejbql, hql );
	}
