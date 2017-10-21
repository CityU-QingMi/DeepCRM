    static void convertToType(Session session, Object[] data, Type[] dataType,
                              Type[] newType) {

        for (int i = 0; i < data.length; i++) {
            if (!dataType[i].canConvertFrom(newType[i])) {
                data[i] = newType[i].convertToType(session, data[i],
                                                   dataType[i]);
            }
        }
    }
