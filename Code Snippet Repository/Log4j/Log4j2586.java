    @Test
    public void testSpecialCharacters() throws Exception {
        testSdfAndFdp("q" ,"", true); // bad pattern character (at present)
        testSdfAndFdp("Q" ,"", true); // bad pattern character
        testSdfAndFdp("$" ,"$", false); // OK
        testSdfAndFdp("?.d" ,"?.12", false); // OK
        testSdfAndFdp("''yyyyMMdd'A''B'HHmmssSSS''", "'20030210A'B153320989'", false); // OK
        testSdfAndFdp("''''yyyyMMdd'A''B'HHmmssSSS''", "''20030210A'B153320989'", false); // OK
        testSdfAndFdp("'$\\Ed'" ,"$\\Ed", false); // OK
        
        // quoted charaters are case sensitive
        testSdfAndFdp("'QED'", "QED", false);
        testSdfAndFdp("'QED'", "qed", true);
        // case sensitive after insensitive Month field
        testSdfAndFdp("yyyy-MM-dd 'QED'", "2003-02-10 QED", false);
        testSdfAndFdp("yyyy-MM-dd 'QED'", "2003-02-10 qed", true);
    }
