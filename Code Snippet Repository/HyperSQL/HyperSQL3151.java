    static Vector getFileLines(File f) throws IOException {

        LineNumberReader read = new LineNumberReader(new FileReader(f));
        Vector           v    = new Vector();

        for (;;) {
            String line = read.readLine();

            if (line == null) {
                break;
            }

            v.addElement(line);
        }

        read.close();

        return v;
    }
