        public String getMessage() {    // override

            String message = super.getMessage() + " magic: ";

            message = message + ((magic == null) ? "null"
                                                 : "'"
                                                   + StringConverter.byteArrayToHexString(magic)
                                                   + "'");

            return message;
        }
