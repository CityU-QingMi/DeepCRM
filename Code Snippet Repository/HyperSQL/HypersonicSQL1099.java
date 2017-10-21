    void setIndexRoots(Session session, String s) {

        if (!isCached) {
            throw Error.error(ErrorCode.X_42501, tableName.name);
        }

        int       indexCount  = getIndexCount();
        ParserDQL p = new ParserDQL(session, new Scanner(session, s), null);
        long[]    roots       = new long[indexCount];
        long[]    uniqueSize  = new long[indexCount];
        long      cardinality = -1;

        p.read();

        for (int index = 0; index < indexCount; index++) {
            long v = p.readBigint();

            roots[index] = v;
        }

        try {
            for (int index = 0; index < indexCount; index++) {
                long v = p.readBigint();

                uniqueSize[index] = v;
            }

            cardinality = p.readBigint();
        } catch (Exception e) {

            // version 1.x database
        }

        setIndexRoots(roots, uniqueSize, cardinality);
    }
