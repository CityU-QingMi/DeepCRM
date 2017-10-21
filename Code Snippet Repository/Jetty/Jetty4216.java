    private static String getTextLineFromStream(InputStream is) throws IOException
    {
        StringBuilder buffer = new StringBuilder();
        int b;

        while ((b = is.read()) != -1 && b != '\n')
        {
            buffer.append((char)b);
        }
        return buffer.toString().trim();
    }
