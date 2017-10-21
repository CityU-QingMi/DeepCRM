    public String readProperty() throws IOException {
        commentLines.clear();
        StringBuilder buffer = new StringBuilder();

        while (true) {
            String line = readLine();
            if (line == null) {
                // EOF
                return null;
            }

            if (isCommentLine(line)) {
                commentLines.add(line);
                continue;
            }

            line = line.trim();

            if (checkCombineLines(line)) {
                line = line.substring(0, line.length() - 1);
                buffer.append(line);
            } else {
                buffer.append(line);
                break;
            }
        }
        return buffer.toString();
    }
