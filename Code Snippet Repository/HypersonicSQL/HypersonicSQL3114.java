    synchronized int xmit(char packetType,
                          org.hsqldb.lib.DataOutputStream destinationStream)
                          throws IOException {

        byte[] ba = byteArrayOutputStream.toByteArray();

        ba[0] = (byte) (ba.length >> 24);
        ba[1] = (byte) (ba.length >> 16);
        ba[2] = (byte) (ba.length >> 8);
        ba[3] = (byte) ba.length;

        reset();
        destinationStream.writeByte(packetType);
        destinationStream.write(ba);
        destinationStream.flush();

        return ba.length;
    }
