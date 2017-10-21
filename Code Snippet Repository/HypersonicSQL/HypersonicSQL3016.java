    public void writeEnd() {

        if (count > storageSize) {
            throw Error.runtimeError(ErrorCode.U_S0500, "RowOutputBinary");
        }

        for (; count < storageSize; ) {
            this.write(0);
        }
    }
