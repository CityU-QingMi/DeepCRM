    String check(String s, String x)
    {
        String r = s;
        int nl = s.indexOf('\n');
        if (nl > 0)
        {
            r = s.substring(nl + 1);
            s = s.substring(0, nl);
        }

        Assert.assertEquals(x, s);

        return r;
    }
