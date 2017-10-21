    public void testEmailValidity() throws Exception {
        assertTrue(verifyEmailValidity("TmJee@Yahoo.com"));
        assertTrue(verifyEmailValidity("tmjee@yahoo.com"));
        assertTrue(verifyEmailValidityWithExpression("tmjee@yahoo.com", "\\b^[a-z]+@[a-z]+(\\.[a-z]+)*\\.com$\\b"));
        assertTrue(verifyEmailValidity("tm_jee@yahoo.co"));
        assertTrue(verifyEmailValidityWithExpression("tm_jee@yahoo.co", "\\b^[a-z_]+@[a-z]+(\\.[a-z]+)*\\.co$\\b"));
        assertTrue(verifyEmailValidity("tm.jee@yahoo.co.uk"));
        assertTrue(verifyEmailValidity("tm.jee@yahoo.co.biz"));
        assertTrue(verifyEmailValidity("tm_jee@yahoo.com"));
        assertTrue(verifyEmailValidity("tm_jee@yahoo.net"));
        assertTrue(verifyEmailValidity(" user@subname1.subname2.subname3.domainname.co.uk "));
        assertTrue(verifyEmailValidity("tm.j'ee@yahoo.co.uk"));
        assertTrue(verifyEmailValidity("tm.j'e.e'@yahoo.co.uk"));
        assertTrue(verifyEmailValidity("tmj'ee@yahoo.com"));
        assertTrue(verifyEmailValidity("ferda+mravenec@yahoo.com"));
        assertTrue(verifyEmailValidity("Ferda+Mravenec@yaHoo.CoM"));
        assertTrue(verifyEmailValidity("user@domainname.tech"));
        assertTrue(verifyEmailValidity("Ferda+Mravenec@yaHoo.cat"));
        assertTrue(verifyEmailValidity("user@domainname.swiss"));

        assertFalse(verifyEmailValidity("tm_jee#marry@yahoo.co.uk"));
        assertFalse(verifyEmailValidity("tm_jee@ yahoo.co.uk"));
        assertFalse(verifyEmailValidity("tm_jee  @yahoo.co.uk"));
        assertFalse(verifyEmailValidity("tm_j ee  @yah oo.co.uk"));
        assertFalse(verifyEmailValidity("tm_jee  @yah oo.co.uk"));
        assertFalse(verifyEmailValidity("tm_jee @ yahoo.com"));
        assertFalse(verifyEmailValidity(" user@subname1.subname2.subname3.domainn#ame.co.uk "));
        assertFalse(verifyEmailValidity("aaa@aa.aaaaaaa"));
        assertFalse(verifyEmailValidity("+ferdamravenec@yahoo.com"));

        assertTrue(verifyEmailValidityWithExpression("tmjee@yahoo.co", "\\b^[a-z]+@[a-z]+(\\.[a-z]+)*\\.com$\\b"));
    }
