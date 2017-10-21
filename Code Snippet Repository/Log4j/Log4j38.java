    @Test
    public void testRB2() {
        final Logger root = Logger.getRootLogger();
        root.setResourceBundle(rbUS);
        ResourceBundle t = root.getResourceBundle();
        assertTrue(t == rbUS);

        final Logger x = Logger.getLogger("x");
        final Logger x_y = Logger.getLogger("x.y");
        final Logger x_y_z = Logger.getLogger("x.y.z");

        x_y.setResourceBundle(rbFR);
        t = x.getResourceBundle();
        assertSame(t, rbUS);
        t = x_y.getResourceBundle();
        assertSame(t, rbFR);
        t = x_y_z.getResourceBundle();
        assertSame(t, rbFR);
    }
