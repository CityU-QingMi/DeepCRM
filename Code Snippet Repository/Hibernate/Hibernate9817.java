    @SuppressWarnings({""})
    private void printAddresses(StringBuilder sb) {
        List<Address> addresses = entityManager.createQuery(
                "select a from Address a order by a.id").getResultList();

        sb.append("Addresses:\n");
        for (Address a : addresses) {
            printAddress(sb, a);
            sb.append("\n");
        }
    }
