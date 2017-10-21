	@Test
    public void testAccessAssociatedSetInPostLoad() {
    	Child child = new Child();
        child.setId(1);
        Parent daddy = new Parent();
        daddy.setId(1);
        child.setDaddy(daddy);
        Set<Child> children = new HashSet<Child>();
        children.add(child);
        daddy.setChildren(children);

        EntityManager em = getOrCreateEntityManager();

        em.getTransaction().begin();
        em.persist(daddy);
        em.getTransaction().commit();
        em.clear();

        daddy = em.find(Parent.class, 1);
        assertEquals(1, daddy.getNrOfChildren());
    }
