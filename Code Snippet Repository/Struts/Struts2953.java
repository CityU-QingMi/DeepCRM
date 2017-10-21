        int copyConstantPool(int constantPoolCount)
            throws UnsupportedEncodingException, IOException {
            int sdeIndex = -1;
            // copy const pool index zero not in class file
            for (int i = 1; i < constantPoolCount; ++i) {
                int tag = readU1();
                writeU1(tag);
                switch (tag) {
                    case 7 : // Class
                    case 8 : // String
                        if (log.isDebugEnabled())
                            log.debug(i + " copying 2 bytes");
                        copy(2);
                        break;
                    case 9 : // Field
                    case 10 : // Method
                    case 11 : // InterfaceMethod
                    case 3 : // Integer
                    case 4 : // Float
                    case 12 : // NameAndType
                        if (log.isDebugEnabled())
                            log.debug(i + " copying 4 bytes");
                        copy(4);
                        break;
                    case 5 : // Long
                    case 6 : // Double
                        if (log.isDebugEnabled())
                            log.debug(i + " copying 8 bytes");
                        copy(8);
                        i++;
                        break;
                    case 1 : // Utf8
                        int len = readU2();
                        writeU2(len);
                        byte[] utf8 = readBytes(len);
                        String str = new String(utf8, "UTF-8");
                        if (log.isDebugEnabled())
                            log.debug(i + " read class attr -- '" + str + "'");
                        if (str.equals(nameSDE)) {
                            sdeIndex = i;
                        }
                        writeBytes(utf8);
                        break;
                    default :
                        throw new IOException("unexpected tag: " + tag);
                }
            }
            return sdeIndex;
        }
