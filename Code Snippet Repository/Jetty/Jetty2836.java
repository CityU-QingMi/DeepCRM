    public void writePoweredBy(Appendable out,String preamble,String postamble) throws IOException
    {
        if (getSendServerVersion())
        {
            if (preamble!=null)
                out.append(preamble);
            out.append(Jetty.POWERED_BY);
            if (postamble!=null)
                out.append(postamble);
        }
    }
