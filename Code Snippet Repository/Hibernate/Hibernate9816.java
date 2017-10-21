    private void printAddress(StringBuilder sb, Address a) {
        sb.append("id = ").append(a.getId()).append(", streetName = ").append(a.getStreetName())
                .append(", houseNumber = ").append(a.getHouseNumber())
                .append(", flatNumber = ").append(a.getFlatNumber())
                .append(", persons = (");

        Iterator<Person> iter = a.getPersons().iterator();
        while (iter.hasNext()) {
            Person p = iter.next();
            sb.append("<").append(p.getId()).append("> ").append(p.getName()).append(" ").append(p.getSurname());
            if (iter.hasNext()) {
                sb.append(", ");
            }
        }

        sb.append(")");
    }
