        @Override
        public byte[] convert(final String value) {
            byte[] bytes;
            if (value == null || value.isEmpty()) {
                bytes = new byte[0];
            } else if (value.startsWith(PREFIX_BASE64)) {
                final String lexicalXSDBase64Binary = value.substring(PREFIX_BASE64.length());
                bytes = Base64Converter.parseBase64Binary(lexicalXSDBase64Binary);
            } else if (value.startsWith(PREFIX_0x)) {
                final String lexicalXSDHexBinary = value.substring(PREFIX_0x.length());
                bytes = HexConverter.parseHexBinary(lexicalXSDHexBinary);
            } else {
                bytes = value.getBytes(Charset.defaultCharset());
            }
            return bytes;
        }
