        private void parse(List<CommandLine> parsedCommands, Stack<String> argumentStack, String[] originalArgs) {
            // first reset any state in case this CommandLine instance is being reused
            isHelpRequested = false;
            CommandLine.this.versionHelpRequested = false;
            CommandLine.this.usageHelpRequested = false;

            parsedCommands.add(CommandLine.this);
            List<Field> required = new ArrayList<Field>(requiredFields);
            Set<Field> initialized = new HashSet<Field>();
            Collections.sort(required, new PositionalParametersSorter());
            try {
                processArguments(parsedCommands, argumentStack, required, initialized, originalArgs);
            } catch (ParameterException ex) {
                throw ex;
            } catch (Exception ex) {
                int offendingArgIndex = originalArgs.length - argumentStack.size();
                String arg = offendingArgIndex >= 0 && offendingArgIndex < originalArgs.length ? originalArgs[offendingArgIndex] : "?";
                throw ParameterException.create(ex, arg, argumentStack.size(), originalArgs);
            }
            if (!isAnyHelpRequested() && !required.isEmpty()) {
                if (required.get(0).isAnnotationPresent(Option.class)) {
                    throw MissingParameterException.create(required);
                } else {
                    try {
                        processPositionalParameters0(required, true, new Stack<String>());
                    } catch (ParameterException ex) { throw ex;
                    } catch (Exception ex) { throw new IllegalStateException("Internal error: " + ex, ex); }
                }
            }
        }
