    private boolean preprocess() throws PreprocessorException {
        this.stack.clear();

        this.state = CONDITION_NONE;

        // optimization - eliminates a full document copy and a full document
        //                equality test for files with no preprocessor
        //                directives
        if (!this.document.contains(Line.DIRECTIVE_PREFIX)) {
            return false;
        }

        Document originalDocument = new Document(this.document);

        preprocessImpl();

        if (this.state != CONDITION_NONE) {
            throw new PreprocessorException("Missing final #endif"); // NOI18N
        }

        if (Option.isFilter(options)) {
            // Cleanup all directives.

            for (int i = this.document.size() - 1; i >= 0; i--) {
                Line line = resolveLine(this.document.getSourceLine(i));

                if (!line.isType(LineType.VISIBLE)) {
                    this.document.deleteSourceLine(i);
                }
            }
        }

        return (!this.document.equals(originalDocument));
    }
