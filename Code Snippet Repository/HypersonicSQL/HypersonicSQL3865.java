    static String readFile(String file) {

        try {
            FileReader     reader = new FileReader(file);
            BufferedReader read   = new BufferedReader(reader);
            StringBuffer   b      = new StringBuffer();
            String         s      = null;
            int            count  = 0;

            while ((s = read.readLine()) != null) {
                count++;

                b.append(s);
                b.append('\n');
            }

            read.close();
            reader.close();

            return b.toString();
        } catch (IOException e) {
            return e.toString();
        }
    }
