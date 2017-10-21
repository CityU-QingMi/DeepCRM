    @SuppressWarnings({""})
    private void printPersons(StringBuilder sb) {
        List<Person> persons = entityManager.createQuery(
                "select p from Person p order by p.id").getResultList();

        sb.append("Persons:\n");
        for (Person p : persons) {
            printPerson(sb, p);
            sb.append("\n");
        }
    }
