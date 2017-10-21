    public void startPart(String contentType)
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
        out.write(__CRLF);
    }
