    private void readModifyPerson(PrintStream out, Scanner scanner, int personId) {
        Person current = entityManager.find(Person.class, personId);

        if (current == null) {
            out.println("Person with id " + personId + " does not exist.");
            return;
        }

        out.print("Person name (NULL for null, enter for no change, current - " + current.getName() + "): ");
        current.setName(convertString(scanner.nextLine(), current.getName()));

        out.print("Person surname (NULL for null, enter for no change, current - " + current.getSurname() + "): ");
        current.setSurname(convertString(scanner.nextLine(), current.getSurname()));

        out.print("Person address id (NULL for null, enter for no change, current - " +
                (current.getAddress() == null ? "NULL" : current.getAddress().getId()) + "): ");
        readAndSetAddress(scanner, current);
    }
