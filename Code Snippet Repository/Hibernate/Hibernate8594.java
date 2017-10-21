	@Test
	public void testMapOneToOne() throws Exception {
		Map child = new HashMap();
		Map parent = new HashMap();
		Session s = openSession();
		s.beginTransaction();
		child.put("parent", parent);
		child.put("$type$", "ChildMap");
		parent.put("child", child);
		parent.put("$type$", "ParentMap");
		s.save(parent);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Map cm = (Map) s.createQuery("from ChildMap cm where cm.parent is not null").uniqueResult();
		s.delete(cm);
		s.delete( cm.get("parent") );
		s.getTransaction().commit();
		s.close();

		child = new HashMap();
		parent = new HashMap();
		s = openSession();
		s.beginTransaction();
		child.put("parent", parent);
		child.put("$type$", "ChildMap");
		parent.put("child", child);
		parent.put("$type$", "ParentMap");
		s.save(child);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Map pm = (Map) s.createQuery("from ParentMap cm where cm.child is not null").uniqueResult();
		s.delete(pm);
		s.delete( pm.get("child") );
		s.getTransaction().commit();
		s.close();

	}
