    private static void dump(String title, String[] as) {

        Error.printSystemOut(title);
        Error.printSystemOut("----------------------------");

        for (int i = 0; i < as.length; i++) {
            Error.printSystemOut(String.valueOf(as[i]));
        }

        Error.printSystemOut("----------------------------");
    }
