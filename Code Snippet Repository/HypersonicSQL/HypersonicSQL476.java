    String parseSQLFeatureValue() {

        if (!isUndelimitedSimpleName()) {
            throw Error.parseError(ErrorCode.X_42555, token.tokenString,
                                   scanner.getLineNumber());
        }

        String sqlFeature = token.tokenString;
        int    index      = ArrayUtil.find(featureStrings, sqlFeature);

        if (index < 0) {
            throw Error.parseError(ErrorCode.X_42555, token.tokenString,
                                   scanner.getLineNumber());
        }

        read();

        return sqlFeature;
    }
