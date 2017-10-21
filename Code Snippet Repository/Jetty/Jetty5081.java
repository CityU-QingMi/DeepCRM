    public static void decodeTo(InputStream in, MultiMap<String> map, String charset, int maxLength, int maxKeys)
    throws IOException
    {
        if (charset==null)
        {
            if (ENCODING.equals(StandardCharsets.UTF_8))
                decodeUtf8To(in,map,maxLength,maxKeys);
            else
                decodeTo(in,map,ENCODING,maxLength,maxKeys);
        }
        else if (StringUtil.__UTF8.equalsIgnoreCase(charset))
            decodeUtf8To(in,map,maxLength,maxKeys);
        else if (StringUtil.__ISO_8859_1.equalsIgnoreCase(charset))
            decode88591To(in,map,maxLength,maxKeys);
        else if (StringUtil.__UTF16.equalsIgnoreCase(charset))
            decodeUtf16To(in,map,maxLength,maxKeys);
        else
            decodeTo(in,map,Charset.forName(charset),maxLength,maxKeys);
    }
