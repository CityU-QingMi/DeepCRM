    private static String readNullTermdUTF(int reqLength,
                                           java.io.InputStream istream)
                                           throws IOException {

/**/
/**/
        int    bytesRead = 0;
        byte[] ba        = new byte[reqLength + 3];

        ba[0] = (byte) (reqLength >>> 8);
        ba[1] = (byte) reqLength;

        while (bytesRead < reqLength + 1) {
            bytesRead += istream.read(ba, 2 + bytesRead,
                                      reqLength + 1 - bytesRead);
        }

        if (ba[ba.length - 1] != 0) {
            throw new IOException("String not null-terminated");
        }

        for (int i = 2; i < ba.length - 1; i++) {
            if (ba[i] == 0) {
                throw new RuntimeException("Null internal to String at offset "
                                           + (i - 2));
            }
        }

        java.io.DataInputStream dis =
            new java.io.DataInputStream(new ByteArrayInputStream(ba));
        String s = dis.readUTF();

        //String s = java.io.DataInputStream.readUTF(dis);
        // TODO:  Test the previous two to see if one works better for
        // high-order characters.
        dis.close();

        return s;
    }
