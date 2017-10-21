    final boolean load(int offset, boolean changeEntity)
            throws IOException {

        // read characters
        int length = fCurrentEntity.mayReadChunks ?
                (fCurrentEntity.ch.length - offset) :
                (DEFAULT_XMLDECL_BUFFER_SIZE);
        int count = fCurrentEntity.reader.read(fCurrentEntity.ch, offset,
                length);

        // reset count and position
        boolean entityChanged = false;
        if (count != -1) {
            if (count != 0) {
                fCurrentEntity.count = count + offset;
                fCurrentEntity.position = offset;
            }
        }

        // end of this entity
        else {
            fCurrentEntity.count = offset;
            fCurrentEntity.position = offset;
            entityChanged = true;
            if (changeEntity) {
                endEntity();
                if (fCurrentEntity == null) {
                    throw new EOFException();
                }
                // handle the trailing edges
                if (fCurrentEntity.position == fCurrentEntity.count) {
                    load(0, false);
                }
            }
        }

        return entityChanged;

    }
