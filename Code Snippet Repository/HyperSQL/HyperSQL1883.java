    private boolean enlargeFile(long newFileLength) {

        try {
            long newBufferLength = newFileLength;

            if (!readOnly) {
                newBufferLength = largeBufferSize;
            }

            MapMode mapMode = readOnly ? FileChannel.MapMode.READ_ONLY
                                       : FileChannel.MapMode.READ_WRITE;

            if (!readOnly && file.length() < fileLength + newBufferLength) {
                file.seek(fileLength + newBufferLength - 1);
                file.writeByte(0);
            }

            MappedByteBuffer[] newBuffers =
                new MappedByteBuffer[buffers.length + 1];
            MappedByteBuffer newBuffer = channel.map(mapMode, fileLength,
                newBufferLength);

            System.arraycopy(buffers, 0, newBuffers, 0, buffers.length);

            newBuffers[buffers.length] = newBuffer;
            buffers                    = newBuffers;
            fileLength                 += newBufferLength;

            logger.logDetailEvent("NIO buffer instance, file size "
                                  + fileLength);
        } catch (Throwable e) {
            logger.logDetailEvent("NOI buffer allocate failed, file size "
                                  + newFileLength);

            return false;
        }

        return true;
    }
