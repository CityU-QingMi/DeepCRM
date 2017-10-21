    @Test
    public void testEncodePath()
    {
        // test basic encode/decode
        StringBuilder buf = new StringBuilder();

        buf.setLength(0);
        URIUtil.encodePath(buf,"/foo%23+;,:=/b a r/?info ");
        assertEquals("/foo%2523+%3B,:=/b%20a%20r/%3Finfo%20",buf.toString());

        assertEquals("/foo%2523+%3B,:=/b%20a%20r/%3Finfo%20",URIUtil.encodePath("/foo%23+;,:=/b a r/?info "));

        buf.setLength(0);
        URIUtil.encodeString(buf,"foo%23;,:=b a r",";,= ");
        assertEquals("foo%2523%3b%2c:%3db%20a%20r",buf.toString());

        buf.setLength(0);
        URIUtil.encodePath(buf,"/context/'list'/\"me\"/;<script>window.alert('xss');</script>");
        assertEquals("/context/%27list%27/%22me%22/%3B%3Cscript%3Ewindow.alert(%27xss%27)%3B%3C/script%3E", buf.toString());

        buf.setLength(0);
        URIUtil.encodePath(buf, "test\u00f6?\u00f6:\u00df");
        assertEquals("test%C3%B6%3F%C3%B6:%C3%9F", buf.toString());

        buf.setLength(0);
        URIUtil.encodePath(buf, "test?\u00f6?\u00f6:\u00df");
        assertEquals("test%3F%C3%B6%3F%C3%B6:%C3%9F", buf.toString());
    }
