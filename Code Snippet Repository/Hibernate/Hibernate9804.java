    private void printAddressHistory(StringBuilder sb, int addressId) {
        AuditReader reader = AuditReaderFactory.get(entityManager);

        List addressHistory = reader.createQuery()
                .forRevisionsOfEntity(Address.class, false, true)
                .add(AuditEntity.id().eq(addressId))
                .getResultList();

        if (addressHistory.size() == 0) {
            sb.append("A address with id ").append(addressId).append(" does not exist.\n");
        } else {
            for (Object historyObj : addressHistory) {
                Object[] history = (Object[]) historyObj;
                DefaultRevisionEntity revision = (DefaultRevisionEntity) history[1];
                sb.append("revision = ").append(revision.getId()).append(", ");
                printAddress(sb, (Address) history[0]);
                sb.append(" (").append(revision.getRevisionDate()).append(")\n");
            }
        }
    }
