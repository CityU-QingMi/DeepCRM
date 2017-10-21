    private String quoteTableName(String name) {

        int dot = name.indexOf(".");

        if (dot < 0) {
            int bracket = name.indexOf(" (");

            if (bracket >= 0) {
                name = name.substring(0, bracket);
            }

            return quoteObjectName(name);
        }

        String partOne = name.substring(0, dot);
        String partTwo = name.substring(dot + 1);
        int    bracket = partTwo.indexOf(" (");

        if (bracket >= 0) {
            partTwo = partTwo.substring(0, bracket);
        }

        return quoteObjectName(partOne) + '.' + quoteObjectName(partTwo);
    }
