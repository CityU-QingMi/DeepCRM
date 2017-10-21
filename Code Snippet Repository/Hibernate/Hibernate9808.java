    private void populateTestData() {
        entityManager.getTransaction().begin();

        if (!hasData()) {
            Person p1 = new Person();
            Person p2 = new Person();
            Person p3 = new Person();

            Address a1 = new Address();
            Address a2 = new Address();

            p1.setName("James");
            p1.setSurname("Bond");
            p1.setAddress(a1);

            p2.setName("John");
            p2.setSurname("McClane");
            p2.setAddress(a2);

            p3.setName("Holly");
            p3.setSurname("Gennaro");
            p3.setAddress(a2);

            a1.setStreetName("MI6");
            a1.setHouseNumber(18);
            a1.setFlatNumber(25);
            a1.setPersons(new HashSet<Person>());
            a1.getPersons().add(p1);

            a2.setStreetName("Nakatomi Plaza");
            a2.setHouseNumber(10);
            a2.setFlatNumber(34);
            a2.setPersons(new HashSet<Person>());
            a2.getPersons().add(p2);
            a2.getPersons().add(p3);

            entityManager.persist(a1);
            entityManager.persist(a2);

            entityManager.persist(p1);
            entityManager.persist(p2);
            entityManager.persist(p3);

            System.out.println("The DB was populated with example data.");
        }

        entityManager.getTransaction().commit();
    }
