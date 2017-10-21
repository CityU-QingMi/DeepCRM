    public void startPart(String contentType, String[] headers)
         throws IOException
    {
        if (inPart)
            out.write(__CRLF);
        out.write(__DASHDASH);
        out.write(boundary);
        out.write(__CRLF);
        out.write("Content-Type: ");
        out.write(contentType);
        out.write(__CRLF);
        for (int i=0;headers!=null && i<headers.length;i++)
        {
            out.write(headers[i]);
            out.write(__CRLF);
        }
        out.write(__CRLF);
        inPart=true;
    }
