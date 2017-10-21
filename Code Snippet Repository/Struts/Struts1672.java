    public static boolean compare(URL url, String text)
        throws Exception {
/**/
/**/
/**/
/**/
        String writerString = TestUtils.normalize(text, true);
        String bufferString = TestUtils.normalize(readContent(url), true);

        return bufferString.equals(writerString);
    }
