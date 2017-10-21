    public void testGetText() throws Exception {
        // we should get the text from the 1st text provider
        assertEquals(textProvider.getText("name"), "1 name");
        assertEquals(textProvider.getText("age"), "1 age");
        assertEquals(textProvider.getText("dog"), "This is a dog");
        assertEquals(textProvider.getText("cat"), "This is a cat");
        assertEquals(textProvider.getText("car"), "This is a car");
        assertEquals(textProvider.getText("bike"), "This is a bike");
        assertEquals(textProvider.getText("someNonExistingKey"), "someNonExistingKey");
    }
