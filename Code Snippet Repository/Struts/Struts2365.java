    public void testNotFindableName() {
        String tokenName = "foo";
        TokenTag tag = new TokenTag();
        tag.setName(tokenName);
        doTokenTest(tokenName, tag);

        String s = writer.toString();
        assertTrue(s.indexOf("name=\"" + TokenHelper.TOKEN_NAME_FIELD) > -1);
        assertTrue(s.indexOf("value=\"" + tokenName + "\"") > -1);
        assertTrue(s.indexOf("name=\"" + tokenName + "\"") > -1);

        //System.out.println(s);
    }
