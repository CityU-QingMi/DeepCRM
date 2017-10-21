    private void printPersonAtRevision(StringBuilder sb, int personId, int revision) {
        AuditReader reader = AuditReaderFactory.get(entityManager);

        Person p = reader.find(Person.class, personId, revision);
        if (p == null) {
            sb.append("This person does not exist at that revision.");
        } else {
            printPerson(sb, p);
        }
    }
