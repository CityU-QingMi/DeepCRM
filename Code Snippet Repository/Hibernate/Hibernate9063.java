    @Test
    public void testNotInitializedProxyCanBeUnproxiedWithInitialization() {
        Parent p = new Parent();
        Child c = new Child();
        p.setChild(c);
        doInJPA(this::entityManagerFactory, (entityManager -> {
            entityManager.persist(p);
        }));
        doInJPA(this::entityManagerFactory, (entityManager -> {
            Parent parent = entityManager.find(Parent.class, p.getId());
            Child child = parent.getChild();
            assertFalse(Hibernate.isInitialized(child));
            Child unproxiedChild = (Child) Hibernate.unproxy(child);
            assertTrue(Hibernate.isInitialized(child));
            assertEquals(Child.class, unproxiedChild.getClass());
        }));
    }
