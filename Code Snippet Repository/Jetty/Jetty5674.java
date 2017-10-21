    @Test
    public void testUtf8_MultiByteCodePoint()
    {
        String input = "text=test%C3%A4";
        UrlEncoded url_encoded = new UrlEncoded();
        url_encoded.decode(input);
    
        // http://www.ltg.ed.ac.uk/~richard/utf-8.cgi?input=00e4&mode=hex
        // Should be "testä"
        // "test" followed by a LATIN SMALL LETTER A WITH DIAERESIS
    
        String expected = "test\u00e4";
        assertThat(url_encoded.getString("text"),is(expected));
    }
