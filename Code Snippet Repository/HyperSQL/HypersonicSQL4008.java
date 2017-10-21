    private void preprocessImpl() throws PreprocessorException {
        int     includeCount = 0;
        int     lineCount    = 0;

        while (lineCount < this.document.size()) {

            try {
                Line line = resolveLine(this.document.getSourceLine(lineCount));

                switch(line.getType()) {
                    case LineType.INCLUDE : {
                        lineCount = processInclude(lineCount, line);

                        break;
                    }
                    case LineType.VISIBLE :
                    case LineType.HIDDEN : {
                        this.document.setSourceLine(lineCount,
                                toSourceLine(line));

                        if (Option.isVerbose(options)) {
                                log((isHidingLines() ? "Commented: "
                                                     : "Uncommented: ") + line);
                        }

                        lineCount++;

                        break;
                    }
                    default : {
                        processDirective(line);

                        lineCount++;
                    }
                }
            } catch (PreprocessorException ex) {
                throw new PreprocessorException(ex.getMessage() + " at line "
                        + (lineCount + 1)
                        + " in \""
                        + this.documentPath
                        + "\""); // NOI18N
            }
        }
    }
