    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
            // value, isEmpty
            {null, true},
            {"", true},
            {new Object[0], true},
            {new ArrayList<>(), true},
            {new HashMap<>(), true},
            {0, false},
            {1, false},
            {false, false},
            {true, false},
            {new Object[]{null}, false},
            {Collections.singletonList(null), false},
            {Collections.singletonMap("", null), false},
            {"null", false}
        };
    }
