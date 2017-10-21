    private void printAddressAtRevision(StringBuilder sb, int addressId, int revision) {
        AuditReader reader = AuditReaderFactory.get(entityManager);

        Address a = reader.find(Address.class, addressId, revision);
        if (a == null) {
            sb.append("This address does not exist at that revision.");
        } else {
            printAddress(sb, a);
        }
    }
