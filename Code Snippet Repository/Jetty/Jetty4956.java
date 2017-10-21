    public void startPart(String contentType, String[] headers)
         throws IOException
    {
        if (inPart)
            out.write(__CRLF);
        inPart=true;
        out.write(__DASHDASH);
        out.write(boundaryBytes);
        out.write(__CRLF);
        if (contentType != null)
            out.write(("Content-Type: "+contentType).getBytes(StandardCharsets.ISO_8859_1));
        out.write(__CRLF);
        for (int i=0;headers!=null && i<headers.length;i++)
        {
            out.write(headers[i].getBytes(StandardCharsets.ISO_8859_1));
            out.write(__CRLF);
        }
        out.write(__CRLF);
    }
