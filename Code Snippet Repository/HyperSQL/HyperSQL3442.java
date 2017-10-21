    HsqlName readLabel() {

        HsqlName label = readNewSchemaObjectName(SchemaObject.LABEL, false);

        if (token.tokenType != Tokens.COLON) {
            throw unexpectedToken(label.getNameString());
        }

        readThis(Tokens.COLON);

        return label;
    }
