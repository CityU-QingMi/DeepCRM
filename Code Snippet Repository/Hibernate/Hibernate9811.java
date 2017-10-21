    private void printPersonHistory(StringBuilder sb, int personId) {
        AuditReader reader = AuditReaderFactory.get(entityManager);

        List personHistory = reader.createQuery()
                .forRevisionsOfEntity(Person.class, false, true)
                .add(AuditEntity.id().eq(personId))
                .getResultList();

        if (personHistory.size() == 0) {
            sb.append("A person with id ").append(personId).append(" does not exist.\n");
        } else {
            for (Object historyObj : personHistory) {
                Object[] history = (Object[]) historyObj;
                DefaultRevisionEntity revision = (DefaultRevisionEntity) history[1];
                sb.append("revision = ").append(revision.getId()).append(", ");
                printPerson(sb, (Person) history[0]);
                sb.append(" (").append(revision.getRevisionDate()).append(")\n");
            }
        }
    }
