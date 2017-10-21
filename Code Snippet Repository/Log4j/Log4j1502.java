        private void processPositionalParameters0(Collection<Field> required, boolean validateOnly, Stack<String> args) throws Exception {
            int max = -1;
            for (Field positionalParam : positionalParametersFields) {
                Range indexRange = Range.parameterIndex(positionalParam);
                max = Math.max(max, indexRange.max);
                @SuppressWarnings("unchecked")
                Stack<String> argsCopy = reverse((Stack<String>) args.clone());
                if (!indexRange.isVariable) {
                    for (int i = argsCopy.size() - 1; i > indexRange.max; i--) {
                        argsCopy.removeElementAt(i);
                    }
                }
                Collections.reverse(argsCopy);
                for (int i = 0; i < indexRange.min && !argsCopy.isEmpty(); i++) { argsCopy.pop(); }
                Range arity = Range.parameterArity(positionalParam);
                assertNoMissingParameters(positionalParam, arity.min, argsCopy);
                if (!validateOnly) {
                    applyOption(positionalParam, Parameters.class, arity, false, argsCopy, null);
                    required.remove(positionalParam);
                }
            }
            // remove processed args from the stack
            if (!validateOnly && !positionalParametersFields.isEmpty()) {
                int processedArgCount = Math.min(args.size(), max < Integer.MAX_VALUE ? max + 1 : Integer.MAX_VALUE);
                for (int i = 0; i < processedArgCount; i++) { args.pop(); }
            }
        }
