    public void testDefaultEncoding() throws Exception {
        // given
        JSONResult json = new JSONResult();
        json.setDefaultEncoding("UTF-16");

        // when
        String encoding = json.getEncoding();

        // thn
        assertEquals("UTF-16", encoding);
    }
