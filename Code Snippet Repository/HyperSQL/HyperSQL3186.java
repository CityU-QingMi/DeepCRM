    static void writeFile(String file, String text) {

        try {
            FileWriter write = new FileWriter(file);

            write.write(text.toCharArray());
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
