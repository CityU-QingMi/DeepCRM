        private HsqlName(HsqlNameManager man, String name, int type,
                         boolean isQuoted) {

            this(man, type);

            this.name          = name;
            this.statementName = name;
            this.isNameQuoted  = isQuoted;

            if (isNameQuoted) {
                statementName = StringConverter.toQuotedString(name, '"',
                        true);
            }
        }
