    public static void main(String[] sa) throws IOException {
        if (sa.length != 2)
            throw new IllegalArgumentException(
                    "SYNTAX: java " + FileRecordReader.class.getName()
                    + " file.txt RECORD_DELIM");
        FileRecordReader frr = new FileRecordReader(sa[0], sa[1], "UTF-8");
        int i = 0;
        String r;
        while ((r = frr.nextRecord()) != null)
            System.out.println("Rec #" + (++i) + ":  [" + r + ']');
    }
