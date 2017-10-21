    synchronized void write(String s, boolean nullTerm) throws IOException {

        stringWriterDos.writeUTF(s);
        write(stringWriterOS.toByteArray(), 2, stringWriterOS.size() - 2);
        stringWriterOS.reset();

        if (nullTerm) {
            writeByte(0);
        }
    }
