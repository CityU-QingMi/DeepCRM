	@Test
	@SuppressWarnings( {""})
    public void testSaveParentWithDetachedChildren() throws Exception {
        Session s = openSession();
        Transaction t = s.beginTransaction();

        Parent p = new Parent("alex");
        Child c = new Child("billy", p);

        s.persist(p);
        s.persist(c);
        t.commit();
        s.close();

        s = openSession();
        t = s.beginTransaction();

        p = (Parent) s.createCriteria(Parent.class)
				.add( Restrictions.eq("name", "alex") )
				.setFetchMode("children", FetchMode.JOIN)
        .setCacheable(true)
        .uniqueResult();

        t.commit();
        s.close();

        s = openSession();
        t = s.beginTransaction();

        Child c2 = new Child("joey", p);
        p.getChildren().add(c2);

        s.update(p);

        // this fails if AbstractEntityPersister returns identifiers instead of entities from
        // AbstractEntityPersister.getNaturalIdSnapshot()
        s.flush();

        s.delete(p);
        t.commit();
        s.close();
    }
