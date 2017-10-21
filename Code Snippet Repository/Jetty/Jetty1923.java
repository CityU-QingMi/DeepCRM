    @Test
    public void testMailSessionReference () throws Exception
    {
        InitialContext icontext = new InitialContext();
        MailSessionReference sref = new MailSessionReference();
        sref.setUser("janb");
        sref.setPassword("OBF:1xmk1w261z0f1w1c1xmq");
        Properties props = new Properties ();
        props.put("mail.smtp.host", "xxx");
        props.put("mail.debug", "true");
        sref.setProperties(props);
        NamingUtil.bind(icontext, "mail/Session", sref);
        Object x = icontext.lookup("mail/Session");
        assertNotNull(x);
        assertTrue(x instanceof javax.mail.Session);
        javax.mail.Session session = (javax.mail.Session)x;
        Properties sessionProps =  session.getProperties();
        assertEquals(props, sessionProps);
        assertTrue (session.getDebug());

        Context foo = icontext.createSubcontext("foo");
        NameParser parser = icontext.getNameParser("");
        Name objectNameInNamespace = parser.parse(icontext.getNameInNamespace());
        objectNameInNamespace.addAll(parser.parse("mail/Session"));

        NamingUtil.bind(foo, "mail/Session", new LinkRef(objectNameInNamespace.toString()));

        Object o = foo.lookup("mail/Session");
        assertNotNull(o);
        Session fooSession = (Session)o;
        assertEquals(props, fooSession.getProperties());
        assertTrue(fooSession.getDebug());

        icontext.destroySubcontext("mail");
        icontext.destroySubcontext("foo");
    }
