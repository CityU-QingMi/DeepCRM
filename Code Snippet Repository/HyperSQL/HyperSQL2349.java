    String readString(int len) throws IOException {

/**/
/**/
        int    bytesRead = 0;
        int    i;
        byte[] ba = new byte[len + 2];

        ba[0] = (byte) (len >>> 8);
        ba[1] = (byte) len;

        while ((i = read(ba, 2 + bytesRead, len - bytesRead)) > -1
                && bytesRead < len) {
            bytesRead += i;
        }

        if (bytesRead != len) {
            throw new EOFException("Packet ran dry");
        }

        for (i = 2; i < ba.length - 1; i++) {
            if (ba[i] == 0) {
                throw new RuntimeException("Null internal to String at offset "
                                           + (i - 2));
            }
        }

        DataInputStream dis =
            new DataInputStream(new ByteArrayInputStream(ba));
        String s = dis.readUTF();

        //String s = DataInputStream.readUTF(dis);
        // TODO:  Test the previous two to see if one works better for
        // high-order characters.
        dis.close();

        return s;
    }
