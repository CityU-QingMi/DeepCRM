    @Test
    public void testProxyEquality() {
        Parent parent = doInJPA(this::entityManagerFactory, (entityManager -> {
            Parent p = new Parent();
            p.name = "John Doe";
            entityManager.persist(p);
            return p;
        }));
        doInJPA(this::entityManagerFactory, (entityManager -> {
            Parent p = entityManager.getReference(Parent.class, parent.getId());
            assertFalse(parent.equals(p));
            assertTrue(parent.equals(Hibernate.unproxy(p)));
        }));
    }
