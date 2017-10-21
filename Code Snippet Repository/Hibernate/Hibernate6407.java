	@Test
	public void testOrderByName() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Products p = new Products();
		HashSet<Widgets> set = new HashSet<Widgets>();

		Widgets widget = new Widgets();
		widget.setName("hammer");
		set.add(widget);
		s.persist(widget);

		widget = new Widgets();
		widget.setName("axel");
		set.add(widget);
		s.persist(widget);

		widget = new Widgets();
		widget.setName("screwdriver");
		set.add(widget);
		s.persist(widget);

		p.setWidgets(set);
		s.persist(p);
		tx.commit();

		tx = s.beginTransaction();
		s.clear();
		p = (Products) s.get(Products.class,p.getId());
		Assert.assertTrue("has three Widgets", p.getWidgets().size() == 3);
		Iterator iter = p.getWidgets().iterator();
		Assert.assertEquals( "axel", ((Widgets)iter.next()).getName() );
		Assert.assertEquals( "hammer", ((Widgets)iter.next()).getName() );
		Assert.assertEquals( "screwdriver", ((Widgets)iter.next()).getName() );
		tx.commit();
		s.close();
	}
