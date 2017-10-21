    public static void main(String[] argv) {

        TestUtil.deleteDatabase("test");
        TestUtil.deleteDatabase("test1");
        TestUtil.deleteDatabase("test2");
        TestUtil.deleteDatabase("test3");
        TestUtil.deleteDatabase("/hsql/testbench/test");
        TestUtil.deleteDatabase("/hsql/jdbcbench/test");
        TestUtil.deleteDatabase("/hsql/test/subselect");
        TestUtil.deleteDatabase("/hsql/test/testpersistent");
        TestUtil.deleteDatabase("/hsql/testdima/test");
        TestUtil.deleteDatabase("/hsql/testpa/test");
        TestUtil.deleteDatabase("/hsql/testtime/test");
    }
