        void copyMembers() {
            int count = readU2();
            writeU2(count);
            if (log.isDebugEnabled())
                log.debug("members count: " + count);
            for (int i = 0; i < count; ++i) {
                copy(6); // access, name, descriptor
                int attrCount = readU2();
                writeU2(attrCount);
                if (log.isDebugEnabled())
                    log.debug("member attr count: " + attrCount);
                copyAttrs(attrCount);
            }
        }
