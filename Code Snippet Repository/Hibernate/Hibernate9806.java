    private Address readNewAddress(PrintStream out, Scanner scanner) {
        Address a = new Address();

        out.print("Street name (NULL for null): ");
        a.setStreetName(convertString(scanner.nextLine(), ""));

        out.print("House number: ");
        a.setHouseNumber(convertStringToInteger(scanner.nextLine(), 0));

        out.print("Flat number: ");
        a.setFlatNumber(convertStringToInteger(scanner.nextLine(), 0));

        a.setPersons(new HashSet<Person>());

        return a;
    }
