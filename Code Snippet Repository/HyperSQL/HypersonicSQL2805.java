    void setLittleEndianByteOrderMark() {

        if ("UTF-16".equals(stringEncoding)) {
            charEncoding   = "UTF-16LE";
            isLittleEndian = true;
            hasUTF16BOM    = true;

            // normal - BOM is expected
        } else {

            // abnormal - no BOM allowed - must use "UTF-16" as encoding
            throw Error.error(ErrorCode.X_S0531);
        }
    }
