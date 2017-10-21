	@Test
	public void testIsEmpty() throws Exception {
		//String hql = "from Animal a where not exists (from a.offspring)";
		String hql = "from Animal a where not exists elements(a.offspring)";
		String ejbql = "select object(a) from Animal a where a.offspring is empty";
		//parse(hql);
		//parse(ejbql);
		assertEjbqlEqualsHql(ejbql, hql);

		hql = "from Animal a where exists (from a.mother.father.offspring)";
		ejbql = "select object(a) from Animal a where a.mother.father.offspring is not empty";
		assertEjbqlEqualsHql( ejbql, hql );
	}
