    synchronized void writeSized(String s) throws IOException {

        stringWriterDos.writeUTF(s);

        byte[] ba = stringWriterOS.toByteArray();

        stringWriterOS.reset();
        writeInt(ba.length - 2);
        write(ba, 2, ba.length - 2);
    }
