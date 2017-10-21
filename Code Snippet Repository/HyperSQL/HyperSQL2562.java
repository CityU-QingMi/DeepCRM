    public static void main(String[] argv) {

        try {
            TestHashStructures test = new TestHashStructures("testHashMap");

            test.testHashMap();
            test.testIntKeyHashMap();
            test.testHashMappedList();
            test.testDoubleIntLookup();
            test.testDoubleIntSpeed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
