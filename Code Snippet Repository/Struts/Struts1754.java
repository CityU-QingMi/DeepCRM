    public void testClearInvalidatesTheSession() throws Exception {
        List<String> attributeNames = new ArrayList<String>();
        attributeNames.add("test");
        attributeNames.add("anotherTest");
        Enumeration attributeNamesEnum = Collections.enumeration(attributeNames);

        MockSessionMap sessionMap = new MockSessionMap((HttpServletRequest) requestMock.proxy());
        sessionMock.expect("setAttribute",
                new Constraint[] {
                    new IsEqual("test"), new IsEqual("test value")
                });
        sessionMock.expect("setAttribute",
                new Constraint[] {
                    new IsEqual("anotherTest"), new IsEqual("another test value")
                });
        sessionMock.expectAndReturn("getAttributeNames", attributeNamesEnum);
        sessionMock.expect("removeAttribute",
                new Constraint[]{
                    new IsEqual("test")
                });
        sessionMock.expect("removeAttribute",
                new Constraint[]{
                    new IsEqual("anotherTest")
                });
        sessionMap.put("test", "test value");
        sessionMap.put("anotherTest", "another test value");
        sessionMap.clear();
        assertNull(sessionMap.get("test"));
        assertNull(sessionMap.get("anotherTest"));
        sessionMock.verify();
    }
