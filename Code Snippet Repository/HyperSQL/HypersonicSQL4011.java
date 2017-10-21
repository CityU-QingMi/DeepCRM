    private int processInclude(int lineCount, Line line)
    throws PreprocessorException {
        String    path   = resolvePath(line.getArguments());
        boolean   hidden = isHidingLines();

        lineCount++;

        while (lineCount < this.document.size()) {
            line = resolveLine(this.document.getSourceLine(lineCount));

            if (line.isType(LineType.ENDINCLUDE)) {
                break;
            }

            this.document.deleteSourceLine(lineCount);
        }

        if (!line.isType(LineType.ENDINCLUDE)) {
            throw new PreprocessorException("Missing #endinclude"); // NOI18N
        }

        if (!hidden) {
            Document     include      = loadInclude(path);
            Preprocessor preprocessor = new Preprocessor(this, include);

            preprocessor.preprocess();

            int count = include.size();

            for (int i = 0; i < count; i++) {
                String sourceLine = include.getSourceLine(i);

                if (resolveLine(sourceLine).isType(LineType.VISIBLE)) {
                    this.document.insertSourceLine(lineCount++, sourceLine);
                }
            }
        }

        lineCount++;

        return lineCount;
    }
