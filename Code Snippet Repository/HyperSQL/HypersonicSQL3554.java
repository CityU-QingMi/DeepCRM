    public Object convertJavaToSQL(SessionInterface session, Object a) {

        if (a == null) {
            return null;
        }

        if (a instanceof byte[]) {
            return new BinaryData((byte[]) a, true);
        }

        if (a instanceof UUID) {
            return getBinary((UUID) a);
        }

        throw Error.error(ErrorCode.X_42561);
    }
