    public void testValidUrlWithDefaultRegex() throws Exception {
        URLValidator validator = new URLValidator();

        Pattern pattern = Pattern.compile(validator.getUrlRegex(), Pattern.CASE_INSENSITIVE);

        assertFalse(pattern.matcher("myapp://test.com").matches());
        assertFalse(pattern.matcher("myap://test.com").matches());
        assertFalse(pattern.matcher("").matches());
        assertFalse(pattern.matcher("   ").matches());
        assertFalse(pattern.matcher("no url").matches());
        assertFalse(pattern.matcher("http://example.com////////////////////////////////////////////////////////////////////////////////////??").matches());

        assertTrue(pattern.matcher("http://www.opensymphony.com").matches());
        assertTrue(pattern.matcher("https://www.opensymphony.com").matches());
        assertTrue(pattern.matcher("https://www.opensymphony.com:443/login").matches());
        assertTrue(pattern.matcher("http://localhost:8080/myapp").matches());

        assertTrue(pattern.matcher("http://www.legalspace.com/__media__/js/netsoltrademark.php?d=www.a-vos-travaux.fr%2Facheter-un-aspirateur-sans-sac-pas-cher%2F").matches());
        assertTrue(UrlValidator.getInstance().isValid("http://www.legalspace.com/__media__/js/netsoltrademark.php?d=www.a-vos-travaux.fr%2Facheter-un-aspirateur-sans-sac-pas-cher%2F"));

        assertTrue(pattern.matcher("http://www.duadmin.isaev.Infoduadmin.Isaev.info/?a%5B%5D=%3Ca%20href%3Dhttp%3A%2F%2Fwww.aspert.fr%2Fun-seche-cheveux-lisseur-est-il-vraiment-utile%2F%3Eseche%20cheveux%20dyson%20test%3C%2Fa").matches());
        assertTrue(UrlValidator.getInstance().isValid("http://www.duadmin.isaev.Infoduadmin.Isaev.info/?a%5B%5D=%3Ca%20href%3Dhttp%3A%2F%2Fwww.aspert.fr%2Fun-seche-cheveux-lisseur-est-il-vraiment-utile%2F%3Eseche%20cheveux%20dyson%20test%3C%2Fa"));

        assertTrue(pattern.matcher("http://netsol-underconstruction-page-monitor-1.com/__media__/js/netsoltrademark.php?d=www.le-soutien-scolaire.fr%2Favis-et-test-comparatifs-des-robots-multifonctions%2F").matches());
        assertTrue(UrlValidator.getInstance().isValid("http://netsol-underconstruction-page-monitor-1.com/__media__/js/netsoltrademark.php?d=www.le-soutien-scolaire.fr%2Favis-et-test-comparatifs-des-robots-multifonctions%2F"));

        //this will cause test to hang indefinitely using JDK 1.8.0_121, Struts 2.5.10.1 and JUnit 4.5
        assertTrue(pattern.matcher("http://www.javaroad.jp/news/redirect.jsp?link=http://www.forum-course-de-cote.com/que-penser-dune-trottinette-electrique/").matches());
        assertTrue(UrlValidator.getInstance().isValid("http://www.javaroad.jp/news/redirect.jsp?link=http://www.forum-course-de-cote.com/que-penser-dune-trottinette-electrique/"));

        //this will cause test to hang indefinitely using JDK 1.8.0_121, Struts 2.5.10.1 and JUnit 4.5
        assertTrue(pattern.matcher("http://wargame.ch/wc/acw/sub/aotm/guestbook/index.php?page3D183EClearwater20Roofing20Contractors3C/a3E3Ekaldu20non20msg3C/a3E").matches());
        assertTrue(UrlValidator.getInstance().isValid("http://wargame.ch/wc/acw/sub/aotm/guestbook/index.php?page3D183EClearwater20Roofing20Contractors3C/a3E3Ekaldu20non20msg3C/a3E"));

        assertTrue(pattern.matcher("http://253.254.255.1").matches());
        assertTrue(UrlValidator.getInstance().isValid("http://253.254.255.1"));

        assertTrue(pattern.matcher("http://253.254.255.12").matches());
        assertTrue(UrlValidator.getInstance().isValid("http://253.254.255.12"));

        assertTrue(pattern.matcher("http://1.2.3.100").matches());
        assertTrue(UrlValidator.getInstance().isValid("http://1.2.3.100"));

        assertTrue(pattern.matcher("http://1.2.3.255").matches());
        assertTrue(UrlValidator.getInstance().isValid("http://1.2.3.255"));

    }
