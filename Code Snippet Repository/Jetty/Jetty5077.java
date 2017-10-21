    public static URI addPath(URI uri, String path)
    {
        String base = uri.toASCIIString();
        StringBuilder buf = new StringBuilder(base.length()+path.length()*3);
        buf.append(base);
        if (buf.charAt(base.length()-1)!='/')
            buf.append('/');

        int offset=path.charAt(0)=='/'?1:0;
        encodePath(buf,path,offset);

        return URI.create(buf.toString());
    }
