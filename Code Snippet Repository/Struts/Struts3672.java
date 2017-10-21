    private static String checkWebAppArgs(String[] args) {
        int colon = args[0].indexOf(':');
        String name = null;
        try {
            name = args[0].substring(colon + 1);
        } catch (Exception e) {
            //this is OK to skip
        }
        if (name == null || name.equals("")) {
            System.out.println("Error: you must specify the webapp you wish");
            System.out.println("       to deploy. The webapp name must be the");
            System.out.println("       name of the directory found in apps/.");
            System.out.println("");
            System.out.println("Example: java -jar struts-core-VERSION.jar quickstart:sandbox");
            System.exit(1);
        }

        return name;
    }
