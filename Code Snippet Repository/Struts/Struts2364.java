    public void testMultipleTagsWithSameName() {
        String tokenName = "sameName";
        TokenTag tag = new TokenTag();
        tag.setName(tokenName);

        String token = doTokenTest(tokenName, tag);

        TokenTag anotherTag = new TokenTag();
        anotherTag.setName(tokenName);

        String anotherToken = doTokenTest(tokenName, anotherTag);
        assertEquals(token, anotherToken);
    }
