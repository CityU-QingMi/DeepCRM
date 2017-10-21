        public void parseIncomingHex(String... rawhex)
        {
            int parts = rawhex.length;
            byte net[];

            for (int i = 0; i < parts; i++)
            {
                String hex = rawhex[i].replaceAll("\\s*(0x)?","");
                net = TypeUtil.fromHexString(hex);
                parser.parse(ByteBuffer.wrap(net));
            }
        }
