    String readString() throws IOException {

/**/
/**/
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        baos.write((byte) 'X');
        baos.write((byte) 'X');

        // Place-holders to be replaced with short length
        int i;

        while ((i = readByte()) > 0) {
            baos.write((byte) i);
        }

        byte[] ba = baos.toByteArray();

        baos.close();

        int len = ba.length - 2;

        ba[0] = (byte) (len >>> 8);
        ba[1] = (byte) len;

        DataInputStream dis =
            new DataInputStream(new ByteArrayInputStream(ba));
        String s = dis.readUTF();

        //String s = DataInputStream.readUTF(dis);
        // TODO:  Test the previous two to see if one works better for
        // high-order characters.
        dis.close();

        return s;
    }
