	@TestForIssue( jiraKey = "" )
	public void testProxiesInCollections() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		Bar bar = new Bar();
		Bar bar2 = new Bar();
		s.save(bar);
		Serializable bar2id = s.save(bar2);
		baz.setFooArray( new Foo[] { bar, bar2 } );
		HashSet set = new HashSet();
		bar = new Bar();
		s.save(bar);
		set.add(bar);
		baz.setFooSet(set);
		set = new HashSet();
		set.add( new Bar() );
		set.add( new Bar() );
		baz.setCascadingBars(set);
		ArrayList list = new ArrayList();
		list.add( new Foo() );
		baz.setFooBag(list);
		Serializable id = s.save(baz);
		Serializable bid = ( (Bar) baz.getCascadingBars().iterator().next() ).getKey();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		BarProxy barprox = (BarProxy) s.load(Bar.class, bid);
		BarProxy bar2prox = (BarProxy) s.load(Bar.class, bar2id);
		assertTrue(bar2prox instanceof HibernateProxy);
		assertTrue(barprox instanceof HibernateProxy);
		baz = (Baz) s.load(Baz.class, id);
		Iterator i = baz.getCascadingBars().iterator();
		BarProxy b1 = (BarProxy) i.next();
		BarProxy b2 = (BarProxy) i.next();
		assertTrue( ( b1==barprox && !(b2 instanceof HibernateProxy) ) || ( b2==barprox && !(b1 instanceof HibernateProxy) ) ); //one-to-many
		// <many-to-many fetch="select" is deprecated by HHH-8662; so baz.getFooArray()[0] should not be a HibernateProxy.
		assertFalse( baz.getFooArray()[0] instanceof HibernateProxy ); //many-to-many
		assertTrue( baz.getFooArray()[1]==bar2prox );
		if ( !isOuterJoinFetchingDisabled() ) assertTrue( !(baz.getFooBag().iterator().next() instanceof HibernateProxy) ); //many-to-many outer-join="true"
		assertTrue( !(baz.getFooSet().iterator().next() instanceof HibernateProxy) ); //one-to-many
		doDelete( s, "from Baz" );
		doDelete( s, "from Foo" );
		s.getTransaction().commit();
		s.close();
	}
