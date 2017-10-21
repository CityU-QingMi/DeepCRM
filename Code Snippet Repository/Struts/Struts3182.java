    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please supply the name of the report(s) source to compile.");
            System.exit(-1);
        }

        try {
            for (int i = 0; i < args.length; i++) {
                System.out.println("JasperReports Compiling: " + args[i]);
                JasperCompileManager.compileReportToFile(args[i]);
            }
        } catch (JRException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        System.exit(0);
    }
