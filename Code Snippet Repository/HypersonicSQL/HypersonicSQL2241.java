    public static void main(String[] sa)
    throws IOException, TarMalformatException {

        if (sa.length < 1) {
            System.out.println(
                RB.TarGenerator_syntax.getString(DbBackup.class.getName()));
            System.exit(0);
        }

        TarGenerator generator = new TarGenerator(new File(sa[0]), true, null);

        if (sa.length == 1) {
            generator.queueEntry("stdin", System.in, 10240);
        } else {
            for (int i = 1; i < sa.length; i++) {
                generator.queueEntry(new File(sa[i]));
            }
        }

        generator.write();
    }
