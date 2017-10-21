    public static Xid getUniqueXid(final int threadId) {
        final Random random = new Random(System.currentTimeMillis());
        //
        int txnSequenceNumberValue = nextTxnSequenceNumber();
        int threadIdValue = threadId;
        int randomValue = random.nextInt();
        //
        byte[] globalTransactionId = new byte[MAXGTRIDSIZE];
        byte[] branchQualifier = new byte[MAXBQUALSIZE];
        byte[] localIp = getLocalIp();

        System.arraycopy(localIp, 0, globalTransactionId, 0, 4);
        System.arraycopy(localIp, 0, branchQualifier, 0, 4);

        // Bytes 4 -> 7 - unique transaction id.
        // Bytes 8 ->11 - thread id.
        // Bytes 12->15 - random.
        for (int i = 0; i <= 3; i++) {
            globalTransactionId[i + 4] = (byte) (txnSequenceNumberValue % 0x100);
            branchQualifier[i + 4] = (byte) (txnSequenceNumberValue % 0x100);
            txnSequenceNumberValue >>= 8;
            globalTransactionId[i + 8] = (byte) (threadIdValue % 0x100);
            branchQualifier[i + 8] = (byte) (threadIdValue % 0x100);
            threadIdValue >>= 8;
            globalTransactionId[i + 12] = (byte) (randomValue % 0x100);
            branchQualifier[i + 12] = (byte) (randomValue % 0x100);
            randomValue >>= 8;
        }

        return new JDBCXID(UXID_FORMAT_ID, globalTransactionId, branchQualifier);
    }
