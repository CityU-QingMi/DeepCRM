    public static void main(String[] sa)
    throws IOException, TarMalformatException {

        if (sa.length < 1) {
            System.out.println(
                RB.TarReader_syntax.getString(TarReader.class.getName()));
            System.out.println(RB.listing_format.getString());
            System.exit(0);
        }

        File exDir = (sa.length > 1 && sa[1].startsWith("--directory="))
                     ? (new File(sa[1].substring("--directory=".length())))
                     : null;
        int firstPatInd = (exDir == null) ? 2
                                          : 3;

        if (sa.length < firstPatInd
                || ((!sa[0].equals("t")) && !sa[0].equals("x"))) {
            throw new IllegalArgumentException(
                RB.tarreader_syntaxerr.getString(TarReader.class.getName()));
        }

        String[] patternStrings = null;

        if (sa.length > firstPatInd) {
            patternStrings = new String[sa.length - firstPatInd];

            for (int i = firstPatInd; i < sa.length; i++) {
                patternStrings[i - firstPatInd] = sa[i];
            }
        }

        if (sa[0].equals("t") && exDir != null) {
            throw new IllegalArgumentException(RB.dir_x_conflict.getString());
        }

        int dirIndex      = (exDir == null) ? 1
                                            : 2;
        int tarReaderMode = sa[0].equals("t") ? TarReader.LIST_MODE
                                              : TarReader.EXTRACT_MODE;

        new TarReader(new File(sa[dirIndex]), tarReaderMode, patternStrings,
                      null, exDir).read();
    }
