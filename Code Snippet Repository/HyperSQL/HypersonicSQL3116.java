    static void validateInputPacketSize(OdbcPacketInputStream p)
    throws RecoverableOdbcFailure {
        int remaining = -1;
        try {
            remaining = p.available();
        } catch (IOException ioe) {
            // Just ignore here and we will send notification below.
            // If there really is an I/O problem, it will be handled better
            // on the next read.
        }
        if (remaining < 1) {
            return;
        }
        throw new RecoverableOdbcFailure(
            "Client supplied bad length for " + p.packetType
            + " packet.  " + remaining + " bytes available after processing",
            "Bad length for " + p.packetType
            + " packet.  " + remaining + " extra bytes", "08P01");
        // Code here means Protocol Violation
    }
