    private String preprocessCsvQuoting(String s, int lineNum)
            throws SqlToolError {
        StringBuilder sb = new StringBuilder();
        int offset, segLen, prevOffset;
        if (s.indexOf('"') < 0) return s.replaceAll(dsvColSplitter, "\u0002");
        prevOffset = -1;
        SEEK_QUOTEDFIELD:
        while (prevOffset < s.length() - 1) {
            // Get start of next quoted field:
            offset = s.indexOf('"', prevOffset + 1);
            segLen = ((offset < 0) ? s.length() : offset)
                    - (prevOffset + 1);
            if (segLen > 0)
                // Here we insert non-quoted segments, replacing all
                // dsvColSplitters.
                sb.append(s.substring(
                        prevOffset + 1, prevOffset + 1 + segLen)
                        .replaceAll(dsvColSplitter, "\u0002"));
            if (offset < 0) break; // Done with line
            prevOffset = offset;
            while ((offset = s.indexOf(
                    '"', prevOffset + 1)) > -1) {
                if (offset - prevOffset > 1)
                    // Here we insert quoted segments without any "s.
                    sb.append(s.substring(
                            prevOffset + 1, offset));
                prevOffset = offset;
                if (s.length() < offset + 2
                        || s.charAt(offset + 1) != '"')
                    // Field terminated
                    continue SEEK_QUOTEDFIELD;
                // Field-internal ""
                prevOffset++;
                sb.append('"');
            }
            throw new SqlToolError(
                SqltoolRB.csv_quote_unterminated.getString(lineNum));
        }
        return sb.toString();
    }
