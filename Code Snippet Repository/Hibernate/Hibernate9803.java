    private void printPerson(StringBuilder sb, Person p) {
        sb.append("id = ").append(p.getId()).append(", name = ").append(p.getName())
                .append(", surname = ").append(p.getSurname());

        Address a = p.getAddress();
        if (a != null) {
            sb.append(", address = <").append(a.getId()).append("> ").append(a.getStreetName()).append(" ")
                    .append(a.getHouseNumber()).append("/").append(a.getFlatNumber());
        }
    }
