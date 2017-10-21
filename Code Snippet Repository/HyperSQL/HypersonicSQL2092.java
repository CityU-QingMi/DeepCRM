    public HsqlArrayList getSection() {

        String        line;
        HsqlArrayList list = new HsqlArrayList(new String[8], 0);

        if (nextStartLine != null) {
            list.add(nextStartLine);

            startLineNumber = nextStartLineNumber;
        }

        while (true) {
            boolean newSection = false;

            line = null;

            try {
                line = reader.readLine();
            } catch (Exception e) {}

            if (line == null) {
                nextStartLine = null;

                return list;
            }

            line = line.substring(0, StringUtil.rightTrimSize(line));

            //if the line is blank or a comment, then ignore it
            if (line.length() == 0 || isIgnoredLine(line)) {
                continue;
            }

            if (isNewSectionLine(line)) {
                newSection = true;
            }

            if (newSection) {
                nextStartLine       = line;
                nextStartLineNumber = reader.getLineNumber();

                return list;
            }

            list.add(line);
        }
    }
