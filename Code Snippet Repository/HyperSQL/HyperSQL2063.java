    private void writeCreate(SessionInterface session,
                             DataOutputStream dataOut) throws IOException {

        dataOut.writeByte(mode);
        dataOut.writeInt(databaseID);
        dataOut.writeLong(sessionID);
        dataOut.writeLong(lobID);
        dataOut.writeInt(subType);
        dataOut.writeLong(blockOffset);
        dataOut.writeLong(blockLength);

        switch (subType) {

            case LobResultTypes.REQUEST_CREATE_BYTES :
                dataOut.write(stream, blockLength);
                break;

            case LobResultTypes.REQUEST_CREATE_CHARS :
                dataOut.write(reader, blockLength);
                break;
        }
    }
