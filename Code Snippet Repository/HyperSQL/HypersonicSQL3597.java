    public String convertToString(Object a) {

        if (a == null) {
            return null;
        }

        switch (typeCode) {

            case Types.SQL_CHAR : {
                int slen = ((String) a).length();

                if (precision == 0 || slen == precision) {
                    return (String) a;
                }

                char[] b = new char[(int) precision];

                ((String) a).getChars(0, slen, b, 0);

                for (int i = slen; i < precision; i++) {
                    b[i] = ' ';
                }

                return new String(b);
            }
            case Types.SQL_VARCHAR : {
                return (String) a;
            }
            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "CharacterType");
        }
    }
