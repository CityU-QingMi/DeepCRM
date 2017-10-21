    @Parameters
    public static Collection<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add(new String[]{"c0af"});
        data.add(new String[]{"EDA080"});
        data.add(new String[]{"f08080af"});
        data.add(new String[]{"f8808080af"});
        data.add(new String[]{"e080af"});
        data.add(new String[]{"F4908080"});
        data.add(new String[]{"fbbfbfbfbf"});
        data.add(new String[]{"10FFFF"});
        data.add(new String[]{"CeBaE1BdB9Cf83CeBcCeB5EdA080656469746564"});
        // use of UTF-16 High Surrogates (in codepoint form)
        data.add(new String[]{"da07"});
        data.add(new String[]{"d807"});
        // decoded UTF-16 High Surrogate "\ud807" (in UTF-8 form)
        data.add(new String[]{"EDA087"});
        return data;
    }
