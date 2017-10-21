    static void writeFileLines(Vector v, File f) throws IOException {

        FileWriter write = new FileWriter(f);

        for (int i = 0; i < v.size(); i++) {
            write.write((String) v.elementAt(i));
            write.write(ls);
        }

        write.flush();
        write.close();
    }
