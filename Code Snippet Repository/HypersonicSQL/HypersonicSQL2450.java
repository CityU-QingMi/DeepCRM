    void initNewFile() {

        try {
            fileFreePosition      = initialFreePos;
            fileStartFreePosition = initialFreePos;

            dataFile.seek(LONG_FREE_POS_POS);
            dataFile.writeLong(fileFreePosition);

            int spaceProps = dataFileScale;

            spaceProps |= (database.logger.getDataFileSpaces() << 16);

            dataFile.seek(INT_SPACE_PROPS_POS);
            dataFile.writeInt(spaceProps);

            // set shadowed flag;
            int flags = 0;

            if (database.logger.propIncrementBackup) {
                flags = BitMap.set(flags, FLAG_ISSHADOWED);
            }

            flags = BitMap.set(flags, FLAG_ISSAVED);
            flags = BitMap.set(flags, FLAG_190);

            setFlags(flags);

            is180 = false;
        } catch (Throwable t) {
            throw Error.error(ErrorCode.FILE_IO_ERROR, t);
        }
    }
