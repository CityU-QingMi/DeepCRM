        static StringCreator getStringCreator(Class cl, String encoding) {

            if (byte.class.equals(cl)) {
                return new StringCreatorBytes(encoding);
            } else if (char.class.equals(cl)) {
                return new StringCreatorChars();
            } else {
                throw Error.runtimeError(ErrorCode.U_S0500, "StringCreator");
            }
        }
