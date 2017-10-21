    private Person readNewPerson(PrintStream out, Scanner scanner) {
        Person p = new Person();

        out.print("Person name (NULL for null): ");
        p.setName(convertString(scanner.nextLine(), ""));

        out.print("Person surname (NULL for null): ");
        p.setSurname(convertString(scanner.nextLine(), ""));

        out.print("Person address id (NULL for null): ");
        readAndSetAddress(scanner, p);

        return p;
    }
