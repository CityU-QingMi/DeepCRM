    private void readAndSetAddress(Scanner scanner, Person p) {
        Address old = p.getAddress();

        String input = scanner.nextLine();
        if ("NULL".equals(input)) {
            p.setAddress(null);
            if (old != null) {
                old.getPersons().remove(p);
            }
        } else if ("".equals(input)) {
        } else {
            try {
                Integer id = Integer.valueOf(input);

                Address a = entityManager.find(Address.class, id);

                if (a == null) {
                    System.err.println("Unknown address id, setting to NULL.");
                    p.setAddress(null);
                    if (old != null) {
                        old.getPersons().remove(p);
                    }
                } else {
                    p.setAddress(a);

                    a.getPersons().add(p);

                    if (old != null) {
                        old.getPersons().remove(p);
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid address id, setting to NULL.");
                p.setAddress(null);
                if (old != null) {
                    old.getPersons().remove(p);
                }
            }
        }
    }
