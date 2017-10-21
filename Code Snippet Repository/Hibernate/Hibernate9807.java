    private void readModifyAddress(PrintStream out, Scanner scanner, int addressId) {
        Address current = entityManager.find(Address.class, addressId);

        if (current == null) {
            out.println("Address with id " + addressId + " does not exist.");
            return;
        }

        out.print("Street name (NULL for null, enter for no change, current - " + current.getStreetName() + "): ");
        current.setStreetName(convertString(scanner.nextLine(), current.getStreetName()));

        out.print("House number (enter for no change, current - " + current.getHouseNumber() + "): ");
        current.setHouseNumber(convertStringToInteger(scanner.nextLine(), current.getHouseNumber()));

        out.print("Flat number (enter for no change, current - " + current.getFlatNumber() + "): ");
        current.setFlatNumber(convertStringToInteger(scanner.nextLine(), current.getFlatNumber()));
    }
