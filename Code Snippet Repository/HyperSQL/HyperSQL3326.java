    void setSourceText(String line) throws PreprocessorException {
        this.sourceText = line;
        int pos         = indexOfNonTabOrSpace(line);
        this.indent     = line.substring(0, pos);
        line            = line.substring(pos);

        if (!line.startsWith(DIRECTIVE_PREFIX)) {
            this.text      = line;
            this.arguments = null;
            this.type      = LineType.VISIBLE;
        } else if (line.length() == DIRECTIVE_PREFIX_LENGTH){
            this.text      = "";
            this.arguments = null;
            this.type       = LineType.HIDDEN;
        } else  if (SPACE_CHARS.indexOf(line.
                charAt(DIRECTIVE_PREFIX_LENGTH)) != -1) {
            this.text      = line.substring(DIRECTIVE_PREFIX_LENGTH_PLUS_ONE);
            this.arguments = null;
            this.type      = LineType.HIDDEN;
        } else {
            pos = indexOfTabOrSpace(line, DIRECTIVE_PREFIX_LENGTH_PLUS_ONE);

            if (pos == -1) {
                this.text      = line;
                this.arguments = null;
            } else {
                this.text      = line.substring(0, pos);
                this.arguments = line.substring(pos + 1).trim();
            }

            Integer oType = (Integer) LineType.directives().get(text);

            if (oType == null) {
                throw new PreprocessorException("Unknown directive ["
                        + text + "] in [" + line + "]"); // NOI18N
            }

            this.type = oType.intValue();
        }

    }
