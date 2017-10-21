    Object parseValue() throws PreprocessorException {
        Object value;

        switch(this.tokenizer.next()) {
            case Token.IDENT : {
                String ident = this.tokenizer.getIdent();

                value = this.defines.getDefintion(ident);

                if (value == null) {
                    throw new PreprocessorException("IDENT " + ident
                            + " is not defined at position"
                            + this.tokenizer.getStartIndex()
                            + "in ["
                            + this.tokenizer.getSource()
                            + "]"); // NOI18N
                }

                break;
            }
            case Token.STRING : {
                value = this.tokenizer.getString();

                break;
            }
            case Token.NUMBER : {
                value = this.tokenizer.getNumber();

                break;
            }
            default :{
                throw new PreprocessorException("IDENT, STRING"
                        + "or NUMBER token required at position "
                        + this.tokenizer.getStartIndex()
                        + " in: ["
                        + this.tokenizer.getSource()
                        + "]"); // NOI18N
            }
        }

        return value;
    }
