    static void testScript(Connection aConnection, String aPath) {

/**/
/**/
/**/
/**/
/**/
        File file = new File(aPath);

        try {
            TestUtil.testScript(aConnection, file.getAbsolutePath(),
                                new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("test script file error: " + e.toString());
        }
    }
