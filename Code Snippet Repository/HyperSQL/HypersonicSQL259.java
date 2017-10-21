        public void rename(String name, boolean isquoted) {

            if (manager.sqlRegularNames && name.length() > 128) {
                throw Error.error(ErrorCode.X_42501, name);
            }

            // get rid of the excess
            this.name          = name;
            this.statementName = this.name;
            this.isNameQuoted  = isquoted;

            if (isNameQuoted) {
                statementName = StringConverter.toQuotedString(name, '"',
                        true);
            }

            if (name.startsWith("SYS_")) {
                int length = name.lastIndexOf('_') + 1;

                try {
                    int temp = Integer.parseInt(name.substring(length));

                    if (temp > manager.sysNumber.get()) {
                        manager.sysNumber.set(temp);
                    }
                } catch (NumberFormatException e) {}
            }
        }
