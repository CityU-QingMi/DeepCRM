    void readColumnNameList(OrderedHashSet set, BitMap quotedFlags,
                            boolean readAscDesc) {

        int i = 0;

        while (true) {
            if (session.isProcessingScript()) {

                // for old scripts
                if (!isSimpleName()) {
                    token.isDelimitedIdentifier = true;
                }
            } else {
                checkIsSimpleName();
            }

            if (!set.add(token.tokenString)) {
                throw Error.error(ErrorCode.X_42577, token.tokenString);
            }

            if (quotedFlags != null) {
                quotedFlags.setValue(i, isDelimitedIdentifier());
            }

            read();

            i++;

            if (readAscDesc) {
                if (token.tokenType == Tokens.ASC
                        || token.tokenType == Tokens.DESC) {
                    read();
                }
            }

            if (readIfThis(Tokens.COMMA)) {
                continue;
            }

            break;
        }
    }
