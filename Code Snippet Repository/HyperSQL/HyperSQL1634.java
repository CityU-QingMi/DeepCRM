    private void freeTableSpacePart(long position, int units) {

        for (; units > 0; ) {

            // count can cover more than one file block
            int blockIndex   = (int) (position / fileBlockItemCount);
            int offset       = (int) (position % fileBlockItemCount);
            int currentUnits = fileBlockItemCount - offset;

            if (currentUnits > units) {
                currentUnits = units;
            }

            boolean result = ba.moveToBlock(blockIndex);

            if (result) {
                int setCount = ba.setRange(offset, currentUnits);

                if (setCount != currentUnits) {
                    ba.unsetRange(offset, currentUnits);

                    String s =
                        "space manager error - recovered (block, offset, units) : ("
                        + blockIndex + "," + offset + "," + units + ")";

                    cache.logSevereEvent(s, null);
                }
            } else {
                String s =
                    "space manager error - recovered (block, offset, units) : ("
                    + blockIndex + "," + offset + "," + units + ")";

                cache.logSevereEvent(s, null);
            }

            units    -= currentUnits;
            position += currentUnits;
        }
    }
