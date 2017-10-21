    @Test
    public void testNotHibernateProxyShouldThrowException() {
        Parent p = new Parent();
        Child c = new Child();
        p.setChild(c);
        doInJPA(this::entityManagerFactory, (entityManager -> {
            entityManager.persist(p);
        }));
        doInJPA(this::entityManagerFactory, (entityManager -> {
            Parent parent = entityManager.find(Parent.class, p.getId());
            assertSame(parent, Hibernate.unproxy(parent));
        }));
    }
