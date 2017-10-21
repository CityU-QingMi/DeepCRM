    public Object convertToDefaultType(SessionInterface session, Object a) {

        if (a == null) {
            return null;
        }

        // conversion to Blob via PreparedStatement.setObject();
        if (a instanceof byte[]) {
            return new BinaryData((byte[]) a, false);
        }

        throw Error.error(ErrorCode.X_42561);
    }
