    @Parameters(name = "")
    public static List<String[]> data()
    {
        List<String[]> ret = new ArrayList<>();

        ret.add(new String[] { "/hello", "/hello", null });
        ret.add(new String[] { "/hello%20world", "/hello%20world", null });
        ret.add(new String[] { "/hello;world", "/hello;world", null });
        ret.add(new String[] { "/hello:world", "/hello:world", null });
        ret.add(new String[] { "/hello!world", "/hello!world", null });
        ret.add(new String[] { "/hello?world", "/hello", "world" });
        ret.add(new String[] { "/hello?type=world", "/hello", "type=world" });
        ret.add(new String[] { "/hello?type=wo&rld", "/hello", "type=wo&rld" });
        ret.add(new String[] { "/hello?type=wo%20rld", "/hello", "type=wo%20rld" });
        ret.add(new String[] { "/hello?type=wo+rld", "/hello", "type=wo+rld" });
        ret.add(new String[] { "/It%27s%20me%21", "/It%27s%20me%21", null });
        // try some slash encoding (with case preservation tests)
        ret.add(new String[] { "/hello%2fworld", "/hello%2fworld", null });
        ret.add(new String[] { "/hello%2Fworld", "/hello%2Fworld", null });
        ret.add(new String[] { "/%2f%2Fhello%2Fworld", "/%2f%2Fhello%2Fworld", null });
        // try some "?" encoding (should not see as query string)
        ret.add(new String[] { "/hello%3Fworld", "/hello%3Fworld", null });
        // try some strange encodings (should preserve them)
        ret.add(new String[] { "/hello%252Fworld", "/hello%252Fworld", null });
        ret.add(new String[] { "/hello%u0025world", "/hello%u0025world", null });
        ret.add(new String[] { "/hello-euro-%E2%82%AC", "/hello-euro-%E2%82%AC", null });
        ret.add(new String[] { "/hello-euro?%E2%82%AC", "/hello-euro","%E2%82%AC" });
        // test the ascii control characters (just for completeness)
        for (int i = 0x0; i < 0x1f; i++)
        {
            String raw = String.format("/hello%%%02Xworld",i);
            ret.add(new String[] { raw, raw, null });
        }

        return ret;
    }
