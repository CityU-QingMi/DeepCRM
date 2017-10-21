    private static void copy(String src, String dest) throws IOException {

        File inputFile  = new File(src);
        File outputFile = new File(dest);

        if (!inputFile.exists()) {
            return;
        }

        FileInputStream  in  = new FileInputStream(inputFile);
        FileOutputStream out = new FileOutputStream(outputFile);
        int              c;

        while ((c = in.read()) != -1) {
            out.write(c);
        }

        in.close();
        out.close();
    }
