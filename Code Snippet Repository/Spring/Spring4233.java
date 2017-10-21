    void putAttributes(ByteVector out) {
        if (mainClass != 0) {
            out.putShort(cw.newUTF8("ModuleMainClass")).putInt(2).putShort(mainClass);
        }
        if (packages != null) {
            out.putShort(cw.newUTF8("ModulePackages"))
               .putInt(2 + 2 * packageCount)
               .putShort(packageCount)
               .putByteArray(packages.data, 0, packages.length);
        }
    }
