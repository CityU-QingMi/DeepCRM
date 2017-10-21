    public static void main(String[] args) {
        try {
            SqlTool.objectMain(args);
        } catch (SqlToolException fr) {
            System.err.println(
                    (fr.getMessage() == null) ? fr : fr.getMessage());
            System.exit(fr.exitValue);
        }
        System.exit(0);
    }
